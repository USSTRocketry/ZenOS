SUMMARY = "Sample PyQt5 project with live graphs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://example.py \
           file://requirements.txt \
           file://setup.py"

S = "${WORKDIR}"

inherit setuptools3

RDEPENDS_${PN} = "python3-pyqt5 pyqtgraph python3-numpy"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 example.py ${D}${bindir}/example_qt
}
