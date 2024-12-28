SUMMARY = "Sample PyQt5 project with live graphs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835c6e3e3b5b5b5b5b5b5b5b5b5b5b5"

SRC_URI = "file://example.py \
           file://requirements.txt"

S = "${WORKDIR}"

inherit setuptools3

RDEPENDS_${PN} = "python3-pyqt5 python3-pyqtgraph"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 example.py ${D}${bindir}/example_qt
}
