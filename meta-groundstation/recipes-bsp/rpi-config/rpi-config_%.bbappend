
ENABLE_UART = "1"
DISABLE_OVERSCAN = "1"
HDMI_FORCE_HOTPLUG = "1"
DISABLE_RPI_BOOT_LOGO = "1"
#ENABLE_GPIO_SHUTDOWN = "1"
#GPIO_SHUTDOWN_PIN = "25"
ENABLE_SPI_BUS = "1"
ENABLE_I2C = "1"
KERNEL_MODULE_AUTOLOAD:rpi += "i2c-dev i2c-bcm2708"

# Rpi3 UART fix
do_deploy:append:raspberrypi3-64() {
    # disable Bluetooth to avoid interference with UART
    bbnote "Running config.txt UART fixes for raspberrypi3-64"
    echo "dtoverlay=disable-bt" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
}