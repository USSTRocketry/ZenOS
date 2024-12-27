DESCRIPTION = "USST groundstation system"
LICENSE = "MIT"

IMAGE_INSTALL:append = " rauc \
                        wayland \
                        weston \
                        networkmanager \
                        dbus \
                        "

IMAGE_INSTALL += " nano vim python3"
IMAGE_INSTALL += " python3-pyqt5"

