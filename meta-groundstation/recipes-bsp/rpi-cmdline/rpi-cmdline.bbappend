

ENABLE_UART = "1"

# Fix UART
do_deploy:append:raspberrypi3-64() {
    bbnote "Running cmdline.txt UART fixes for raspberrypi3-64"
    sed -i 's/console=serial0,115200/console=ttyAMA0,115200/' ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/cmdline.txt
}