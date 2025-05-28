DESCRIPTION = "USST groundstation system"
LICENSE = "MIT"

IMAGE_INSTALL:append = " rauc dbus "
IMAGE_LINGUAS ?= " "

# XFCE
IMAGE_INSTALL:append = " packagegroup-core-boot \
    packagegroup-core-x11 \
    packagegroup-xfce-base \
    kernel-modules \
"
inherit features_check
REQUIRED_DISTRO_FEATURES = "x11"
SYSTEMD_DEFAULT_TARGET = "graphical.target"


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
IMAGE_INSTALL += " dhcpcd"
IMAGE_INSTALL += " iptables"
IMAGE_INSTALL += " redis"
IMAGE_INSTALL += " python3-redis"
IMAGE_INSTALL += " telemetry"
IMAGE_INSTALL += " python3-matplotlib"
IMAGE_INSTALL += " python3-circuitpython-rfm9x"
IMAGE_INSTALL += " python3-adafruit-blinka"
IMAGE_INSTALL += " python3-adafruit-platformdetect"
IMAGE_INSTALL += " libgpiod"

