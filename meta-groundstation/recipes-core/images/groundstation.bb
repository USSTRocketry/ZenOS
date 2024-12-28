DESCRIPTION = "USST groundstation system"
LICENSE = "MIT"

IMAGE_INSTALL:append = " rauc \
                        wayland \
                        weston \
                        dbus \
                        "
#networkmanager 
#networkmanager-wifi

IMAGE_INSTALL += " linux-firmware-rpidistro-bcm43430"
IMAGE_INSTALL += " nano"
IMAGE_INSTALL += " vim"
IMAGE_INSTALL += " python3"
IMAGE_INSTALL += " python3-pip"
IMAGE_INSTALL += " python3-pyqt5"
IMAGE_INSTALL += " pyqtgraph"
IMAGE_INSTALL += " python3-numpy"
IMAGE_INSTALL += " example"
IMAGE_INSTALL += " wpa-supplicant"
IMAGE_INSTALL += " iw"
#IMAGE_INSTALL += " iwlist"
IMAGE_INSTALL += " dhcpcd"
IMAGE_INSTALL += " iptables"
