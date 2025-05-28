SUMMARY = "Adafruit Platform Detection for Python3"
HOMEPAGE = "https://github.com/adafruit/Adafruit_Python_PlatformDetect"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fccd531dce4b989c05173925f0bbb76c"

SRC_URI = "git://github.com/adafruit/Adafruit_Python_PlatformDetect.git;branch=main;protocol=https"
SRCREV = "7e3169bc72862c53769e36890f142baab6640678"


PV = "3.79.0"
S = "${WORKDIR}/git"

inherit setuptools3

DEPENDS += "python3"
RDEPENDS:${PN} += "python3"

do_configure:prepend() {
cat > ${S}/setup.py <<-EOF
from setuptools import setup

setup(
       name="Adafruit-PlatformDetect",
       version="${PV}",
       license="${LICENSE}",
)
EOF
}