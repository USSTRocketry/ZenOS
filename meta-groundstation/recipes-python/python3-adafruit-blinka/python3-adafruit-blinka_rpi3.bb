SUMMARY = "CircuitPython compatibility layer for single-board computers"
HOMEPAGE = "https://github.com/adafruit/Adafruit_Blinka"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fccd531dce4b989c05173925f0bbb76c"

SRC_URI = "git://github.com/USSTRocketry/Adafruit_Blinka.git;branch=rpi-3;protocol=https"
SRC_URI[sha256sum] = "8181f61c8c38de8d941b307044042923c2238a999a3c2580c865ef8964a53525"
SRCREV = "${AUTOREV}"

PV = "rpi3"
S = "${WORKDIR}/git"

inherit setuptools3

DEPENDS += "python3-setuptools-scm-native python3"

RDEPENDS:${PN} += " \
    libgpiod \
    python3-adafruit-platformdetect \
    python3-core \
"

# Optional, if you need it:
# RDEPENDS:${PN} += "python3-adafruit-pureio"

RDEPENDS:${PN}:append:rpi = " rpi-gpio"


