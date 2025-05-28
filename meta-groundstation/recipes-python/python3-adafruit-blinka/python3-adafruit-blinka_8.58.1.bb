SUMMARY = "CircuitPython compatibility layer for single-board computers"
HOMEPAGE = "https://github.com/adafruit/Adafruit_Blinka"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fccd531dce4b989c05173925f0bbb76c"

SRC_URI = "git://github.com/adafruit/Adafruit_Blinka.git;branch=main;protocol=https"
SRCREV = "8f45d6cbc540dd809d753fc3af599b63afddf615"

PV = "8.58.1"
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


