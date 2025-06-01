SUMMARY = "Type stubs used by CircuitPython libraries"
HOMEPAGE = "https://github.com/adafruit/Adafruit_CircuitPython_Typing"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a089cc2176ad7f6066833cbef57695b0"

SRC_URI = "git://github.com/adafruit/Adafruit_CircuitPython_Typing.git;branch=main;protocol=https"
SRCREV = "2e1950ca7b1c34ff90331518c1ae643e39a1306b"

PV = "1.11.2"
S = "${WORKDIR}/git"

inherit python_setuptools_build_meta

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN} += "python3-core python3-typing-extensions"
