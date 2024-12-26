
#MACHINE = "raspberrypi4-64"
MACHINE = "raspberrypi3"

EXTRA_IMAGE_FEATURES = "debug-tweaks ssh-server-openssh"
INHERIT += "extrausers"
EXTRA_USERS_PARAMS = "usermod -P /etc/shadow AV@USST pi;"


# Use systemd as the init manager
DISTRO_FEATURES_append = " systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"
VIRTUAL-RUNTIME_initscripts = ""

# Add RAUC to the image
IMAGE_INSTALL_append = " rauc"

# Configure image output formats
IMAGE_FSTYPES = "tar.bz2 ext4 wic.bz2 wic.bmap"
SDIMG_ROOTFS_TYPE = "ext4"

# Enable UART on Raspberry Pi
ENABLE_UART = "1"

# Enable U-Boot as the bootloader
RPI_USE_U_BOOT = "1"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot"

# Use a dual-partition WKS file for Raspberry Pi
WKS_FILE = "sdimage-dual-raspberrypi.wks.in"


IMAGE_INSTALL += " nano vim python3"
#IMAGE_INSTALL += " example"
