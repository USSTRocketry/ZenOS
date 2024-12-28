FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://wpa_supplicant.service"

do_install:append() {
    install -d ${D}${sysconfdir}/wpa_supplicant
    install -m 600 ${WORKDIR}/wpa_supplicant.conf ${D}${sysconfdir}/wpa_supplicant/

    install -d ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/wpa_supplicant.service ${D}${systemd_unitdir}/system/

}

SYSTEMD_SERVICE:${PN}:append = " wpa_supplicant.service "
SYSTEMD_AUTO_ENABLE = "enable"
