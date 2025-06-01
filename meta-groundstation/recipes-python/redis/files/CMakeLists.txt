## Shamelessly copied from https://stackoverflow.com/questions/76654675/how-do-i-create-a-yocto-recipe-for-redistimeseries

cmake_minimum_required (VERSION 3.16.3 FATAL_ERROR)

## CMakeLists.text file to build the redistimeseries.so module

project(redistimeseries LANGUAGES C CXX)
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_C_COMPILER_WORKS 1)
set(CMAKE_BUILD_TYPE "Release")

###### set defines and compile flags ######
find_package(Git REQUIRED)
execute_process(COMMAND ${GIT_EXECUTABLE} -C ${CMAKE_SOURCE_DIR} rev-parse --short HEAD OUTPUT_VARIABLE GIT_VERSION_HASH OUTPUT_STRIP_TRAILING_WHITESPACE)
execute_process(COMMAND ${GIT_EXECUTABLE} -C ${CMAKE_SOURCE_DIR} describe --tags --dirty --match "v*" OUTPUT_VARIABLE GIT_VERSION_STRING OUTPUT_STRIP_TRAILING_WHITESPACE)
string(REGEX MATCH "^v([0-9]+)\\.([0-9]+)\\.([0-9]+)-?([0-9]*)-?g?([a-z0-9]*)-?(dirty)?" MATCH_DUMMY "${GIT_VERSION_STRING}")
set(CMAKE_PROJECT_VERSION_MAJOR ${CMAKE_MATCH_1})
set(CMAKE_PROJECT_VERSION_MINOR ${CMAKE_MATCH_2})
set(CMAKE_PROJECT_VERSION_PATCH ${CMAKE_MATCH_3})
set(CMAKE_PROJECT_VERSION_TWEAK ${CMAKE_MATCH_4})
set(GIT_VERSION_CLEAN ${CMAKE_MATCH_6})
set(CMAKE_PROJECT_VERSION "${CMAKE_PROJECT_VERSION_MAJOR}.${CMAKE_PROJECT_VERSION_MINOR}.${CMAKE_PROJECT_VERSION_PATCH}.${CMAKE_PROJECT_VERSION_TWEAK}")
math(EXPR VERSION "${CMAKE_PROJECT_VERSION_MAJOR} * 10000 + ${CMAKE_PROJECT_VERSION_MINOR} * 100 + ${CMAKE_PROJECT_VERSION_PATCH}")
add_definitions(
    -DREDIS_MODULE_TARGET 
    -DREDISTIMESERIES_GIT_SHA=\"${GIT_VERSION_HASH}\"
    -DREDISMODULE_SDK_RLEC
    -DREDISMODULE_EXPERIMENTAL_API
    -DREDISTIMESERIES_MODULE_VERSION=${VERSION}
    -DREDISTIMESERIES_VERSION_MAJOR=${CMAKE_PROJECT_VERSION_MAJOR}
    -DREDISTIMESERIES_VERSION_MINOR=${CMAKE_PROJECT_VERSION_MINOR}
    -DREDISTIMESERIES_VERSION_PATCH=${CMAKE_PROJECT_VERSION_PATCH})

execute_process(COMMAND ${CMAKE_C_COMPILER} -dumpmachine OUTPUT_VARIABLE CMAKE_SYSTEM_PROCESSOR OUTPUT_STRIP_TRAILING_WHITESPACE)

add_compile_options(-fPIC)
add_link_options(-fPIC)

set(EVENT__DISABLE_OPENSSL OFF CACHE BOOL "enable/disable libevent ssl")
set(EVENT__DISABLE_BENCHMARK ON CACHE BOOL "disable benchmarking")
set(EVENT__DISABLE_TESTS ON CACHE BOOL "disable tests")
set(EVENT__DISABLE_REGRESS ON CACHE BOOL "disable tests")
set(ENABLE_SSL ON CACHE BOOL "enable/disable hiredis ssl")

###### Checkout versions of depencies ######
execute_process(COMMAND ${GIT_EXECUTABLE} -C ${CMAKE_SOURCE_DIR}/deps/LibMR checkout ec5a8ea6e7a7cd6c88fffb27bfde0eef33c75b35)
execute_process(COMMAND ${GIT_EXECUTABLE} -C ${CMAKE_SOURCE_DIR}/deps/LibMR/deps/hiredis checkout v1.1.0)
execute_process(COMMAND ${GIT_EXECUTABLE} -C ${CMAKE_SOURCE_DIR}/deps/LibMR/deps/libevent checkout release-2.1.12-stable)
execute_process(COMMAND ${GIT_EXECUTABLE} -C ${CMAKE_SOURCE_DIR}/deps/fast_double_parser checkout v0.7.0)
execute_process(COMMAND ${GIT_EXECUTABLE} -C ${CMAKE_SOURCE_DIR}/deps/cpu_features checkout v0.8.0)

###### find libraries ######
find_package(OpenSSL REQUIRED)
set(THREADS_PREFER_PTHREAD_FLAG ON)
find_package(Threads REQUIRED)

###### set include dirs #######
include_directories(PUBLIC ${CMAKE_SOURCE_DIR}/deps ${CMAKE_SOURCE_DIR}/deps/RedisModulesSDK ${CMAKE_SOURCE_DIR}/deps/fast_double_parser/include/)

###### libmodule ######
add_library(rmutil STATIC
    deps/RedisModulesSDK/rmutil/util.c
    deps/RedisModulesSDK/rmutil/strings.c
    deps/RedisModulesSDK/rmutil/sds.c
    deps/RedisModulesSDK/rmutil/vector.c
    deps/RedisModulesSDK/rmutil/alloc.c
    deps/RedisModulesSDK/rmutil/periodic.c
)

###### LibMr, hiredis, libevent ######
add_definitions(-DMODULE_NAME=timeseries)
add_subdirectory(${CMAKE_SOURCE_DIR}/deps/LibMR/deps/hiredis EXCLUDE_FROM_ALL)
add_subdirectory(${CMAKE_SOURCE_DIR}/deps/LibMR/deps/libevent EXCLUDE_FROM_ALL)
add_library(mr STATIC
    deps/LibMR/src/cluster.c  
    deps/LibMR/src/crc16.c  
    deps/LibMR/src/event_loop.c  
    deps/LibMR/src/mr.c  
    deps/LibMR/src/record.c
    deps/LibMR/src/utils/adlist.c  
    deps/LibMR/src/utils/dict.c     
    deps/LibMR/src/utils/thpool.c
    deps/LibMR/src/utils/buffer.c  
    deps/LibMR/src/utils/siphash.c
)
target_include_directories(mr PRIVATE ${CMAKE_SOURCE_DIR}/deps/LibMR/deps/hiredis ${CMAKE_SOURCE_DIR}/deps/LibMR/deps/hiredis/adapters)
target_link_libraries(mr PRIVATE hiredis_static event_static)

###### dragonbox ######
add_library(dragonbox STATIC
    deps/dragonbox/dragonbox.cpp
)

###### fast double parser ######
add_subdirectory(${CMAKE_SOURCE_DIR}/deps/fast_double_parser EXCLUDE_FROM_ALL)

###### cpu features ######
set(BUILD_TESTING OFF)
add_subdirectory(${CMAKE_SOURCE_DIR}/deps/cpu_features EXCLUDE_FROM_ALL)

###### redistimeseries ######
add_library(redistimeseries SHARED
    src/chunk.c
    src/compaction.c
    src/compressed_chunk.c
    src/config.c
    src/consts.c
    src/endianconv.c
    src/filter_iterator.c
    src/generic_chunk.c
    src/gorilla.c
    src/indexer.c
    src/libmr_integration.c
    src/libmr_commands.c
    src/module.c
    src/parse_policies.c
    src/query_language.c
    src/reply.c
    src/rdb.c
    src/short_read.c
    src/resultset.c
    src/tsdb.c
    src/series_iterator.c
    src/sample_iterator.c
    src/enriched_chunk.c
    src/multiseries_sample_iterator.c
    src/multiseries_agg_dup_sample_iterator.c
    src/utils/arch_features.c
    src/utils/heap.c
    src/utils/blocked_client.c
    src/fast_double_parser_c/fast_double_parser_c.cpp
)
target_precompile_headers(redistimeseries PRIVATE ${CMAKE_SOURCE_DIR}/src/common.h)
target_include_directories(fast_double_parser INTERFACE ${CMAKE_SOURCE_DIR}/deps/fast_double_parser/include/)
target_link_libraries(redistimeseries PRIVATE rmutil mr event_pthreads_static event_static hiredis_static hiredis_ssl_static dragonbox cpu_features)
target_link_libraries(redistimeseries PUBLIC fast_double_parser ssl crypto dl rt m)
set_target_properties(redistimeseries PROPERTIES PREFIX "")

install(TARGETS redistimeseries LIBRARY)

set(CPACK_GENERATOR "DEB")
set(CPACK_DEBIAN_PACKAGE_MAINTAINER "Xemex")
set(CPACK_PACKAGE_CONTACT "info@xemex.eu")
set(CPACK_PACKAGE_VERSION "${CMAKE_PROJECT_VERSION_MAJOR}.${CMAKE_PROJECT_VERSION_MINOR}.${CMAKE_PROJECT_VERSION_PATCH}-r${CMAKE_PROJECT_VERSION_TWEAK}")

set(PKG_EXTENTION "ipk")
set(CPACK_DEBIAN_PACKAGE_ARCHITECTURE "cortexa7t2hf-neon-mx6ul")

set(CPACK_DEBIAN_FILE_NAME "$redistimeseries_${GIT_VERSION_STRING}_${CPACK_DEBIAN_PACKAGE_ARCHITECTURE}_${CMAKE_BUILD_TYPE}.${PKG_EXTENTION}")

include(CPack)