DESCRIPTION = "RAUC bundle generator"

inherit bundle

RAUC_BUNDLE_COMPATIBLE = "${MACHINE}"
RAUC_BUNDLE_VERSION = "v20200703"
RAUC_BUNDLE_DESCRIPTION = "USST groundstation Bundle"

RAUC_BUNDLE_FORMAT = "verity"

RAUC_BUNDLE_SLOTS = "rootfs"
RAUC_SLOT_rootfs = "groundstation-image"
RAUC_SLOT_rootfs[fstype] = "ext4"

RAUC_KEY_FILE ?= "${THISDIR}/files/development-1.key.pem"
RAUC_CERT_FILE ?= "${THISDIR}/files/development-1.cert.pem"

# Ensure groundstation-image builds first
do_bundle[depends] += "groundstation-image:do_image_complete"