# Shamelessly copied from https://stackoverflow.com/questions/76654675/how-do-i-create-a-yocto-recipe-for-redistimeseries

SUMMARY = "Time Series data structure for Redis"
DESCRIPTION = "RedisTimeSeries is a time-series database (TSDB) module for Redis, by Redis."
HOMEPAGE = "https://github.com/RedisTimeSeries/RedisTimeSeries"
LICENSE = "CLOSED"


SRC_URI = "\
    gitsm://github.com/RedisTimeSeries/RedisTimeSeries;branch=1.8;rev=v1.8.10;protocol=https \
    file://CMakeLists.txt \
"

S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}:"

inherit cmake

DEPENDS = "openssl"

EXTRA_OECMAKE = ""

do_configure:prepend() {
    cp ${WORKDIR}/CMakeLists.txt ${S}/
}

do_install:append() {
    install -d ${D}${libdir}/redis/modules
    install -m 0755 ${B}/redistimeseries.so ${D}${libdir}/redis/modules/
}

FILES:${PN} += " \
    ${libdir}/redis/modules/redistimeseries.so \
    ${libdir}/redistimeseries.so \
"