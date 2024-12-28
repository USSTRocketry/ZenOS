DESCRIPTION = "USST groundstation system"
LICENSE = "MIT"

IMAGE_INSTALL:append = " rauc \
                        wayland \
                        weston \
                        networkmanager \
                        dbus \
                        "

IMAGE_INSTALL += " nano"
IMAGE_INSTALL += " vim"
IMAGE_INSTALL += " python3"
IMAGE_INSTALL += " python3-pip"
IMAGE_INSTALL += " python3-pyqt5"
IMAGE_INSTALL += " python3-pyqtgraph"
IMAGE_INSTALL += " example"