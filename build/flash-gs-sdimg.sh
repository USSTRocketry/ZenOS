#!/bin/bash

# Check if an argument is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <target_device>"
    exit 1
fi

# Verify that the target device exists
if [ ! -b "$1" ]; then
    echo "Error: Target device '$1' does not exist or is not a valid block device."
    exit 1
fi

# Check if the device is mounted
if mount | grep -q "$1"; then
    echo "Error: Target device '$1' is currently mounted."
    exit 1
fi

# Write the image
bzcat tmp/deploy/images/raspberrypi3/groundstation-image-raspberrypi3.rootfs.wic.bz2 | sudo dd of="$1" bs=4M status=progress conv=fsync

