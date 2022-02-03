#!/bin/sh

echo 137 > /sys/class/gpio/export
echo out > /sys/class/gpio/gpio137/direction
echo 1 > /sys/class/gpio/gpio137/value

echo 4 > /sys/class/gpio/export
echo out > /sys/class/gpio/gpio4/direction
echo 1 > /sys/class/gpio/gpio4/value

while true;
do
	sleep 1
done


