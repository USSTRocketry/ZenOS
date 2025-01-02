FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://10-eth.network \
    file://20-en.network \
    file://10-wlan.network \
"

FILES:${PN} += " \
    ${sysconfdir}/systemd/network/10-eth.network \
    ${sysconfdir}/systemd/network/20-en.network \
    ${sysconfdir}/systemd/network/10-wlan.network \
"

do_install:append() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/10-eth.network ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/20-en.network ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/10-wlan.network ${D}${sysconfdir}/systemd/network
}