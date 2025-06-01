SUMMARY = "Adafruit rfm9x Library for Python"
HOMEPAGE = "https://pypi.org/project/adafruit-circuitpython-rfm9x"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=be34639b266a30dd433d48b8a7260454"

SRC_URI = "https://files.pythonhosted.org/packages/0a/d2/22be8ffb05008f568ea7f3ba470abbe4dc6f76a2328590bd31f0569d9470/adafruit_circuitpython_rfm9x-2.2.19.tar.gz"
SRC_URI[sha256sum] = "a973aa35e40b940d8f8b7cb5739cfc627a380ba9575cdd0d866c5a0b894fc9e5"
S = "${WORKDIR}/adafruit_circuitpython_rfm9x-2.2.19"


#inherit setuptools3 python3native
inherit python3native

DEPENDS += "python3"

RDEPENDS:${PN} += "python3-core python3-adafruit-blinka"

#PYPI_PACKAGE = "adafruit-circuitpython-rfm9x"

do_install() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/adafruit_rfm9x
    cp -r ${S}/*.py ${D}${PYTHON_SITEPACKAGES_DIR}/adafruit_rfm9x/
    echo "# RFM9x Package" > ${D}${PYTHON_SITEPACKAGES_DIR}/adafruit_rfm9x/__init__.py
}

FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}"
