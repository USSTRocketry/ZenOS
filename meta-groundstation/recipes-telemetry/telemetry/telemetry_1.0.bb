DESCRIPTION = "Telemetry Display Application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "https://github.com/USSTRocketry/telemetry.git;branch=master \
           file://telemetry.service"

SRC_URI[sha256sum] = "e934bdc9fa20bdebff1edaa234d93232b2564b2d78822a703bb5d949bebb21e3"

SRCREV = "${AUTOREV}"

# Specify the destination for the cloned repo
S = "${WORKDIR}/git"

RDEPENDS_${PN} = "python3 \
                  python3-pyqt5 \
                  pyqtgraph \
                  python3-redis"

inherit systemd
SYSTEMD_SERVICE:${PN}:append = " telemetry.service "

do_install() {
    install -d ${D}${bindir}/telemetry
    cp -r ${S}/. ${D}${bindir}/telemetry/

    # Install the service file
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/telemetry.service ${D}/${systemd_unitdir}/system
}

FILES:${PN} = "${bindir}/telemetry/* \
               ${systemd_unitdir}/system/telemetry.service \
               ${bindir}/telemetry/"
