#!/bin/bash
###
# #%L
# **********************************************************************
# ORGANIZATION  :  Pi4J
# PROJECT       :  Pi4J :: JNI Native Binding Library for PIGPIO
# FILENAME      :  build-prerequisites.sh
#
# This file is part of the Pi4J project. More information about
# this project can be found here:  https://pi4j.com/
# **********************************************************************
# %%
# Copyright (C) 2012 - 2021 Pi4J
# %%
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# #L%
###
# ----------------------------------
# install prerequisites
# ----------------------------------
if [ ! -z "`type apt-get 2>/dev/null;`" ]; then

  # GCC
  GCC_INSTALLED=$(dpkg-query -W --showformat='${Status}\n' gcc|grep "install ok installed")
  if [[ "" == "$GCC_INSTALLED" ]]; then
    sudo apt-get --force-yes --yes install gcc
  else
    echo " [PREREQUISITE] 'gcc' already installed.";
  fi

  # GIT
  GIT_INSTALLED=$(dpkg-query -W --showformat='${Status}\n' git|grep "install ok installed")
  if [[ "" == "$GIT_INSTALLED" ]]; then
    sudo apt-get --force-yes --yes install git
  else
    echo " [PREREQUISITE] 'git' already installed.";
  fi

  # TREE
  TREE_INSTALLED=$(dpkg-query -W --showformat='${Status}\n' tree|grep "install ok installed")
  if [[ "" == "$TREE_INSTALLED" ]]; then
    sudo apt-get --force-yes --yes install tree
  else
    echo " [PREREQUISITE] 'tree' already installed.";
  fi

  # gcc-arm-linux-gnueabihf
  GCC_ARMHF_INSTALLED=$(dpkg-query -W --showformat='${Status}\n' gcc-arm-linux-gnueabihf)
  if [[ "$?" == "1" ]] ; then
    echo " [PREREQUISITE] installing 'gcc-arm-linux-gnueabihf'...";
    sudo apt-get --force-yes --yes install gcc-arm-linux-gnueabihf
  else
    echo " [PREREQUISITE] 'gcc-arm-linux-gnueabihf' already installed.";
  fi

  # gcc-aarch64-linux-gnu
  GCC_AARCH64_INSTALLED=$(dpkg-query -W --showformat='${Status}\n' gcc-aarch64-linux-gnu)
  if [[ "$?" == "1" ]] ; then
    echo " [PREREQUISITE] installing 'gcc-aarch64-linux-gnu'...";
    sudo apt-get --force-yes --yes install gcc-aarch64-linux-gnu
  else
    echo " [PREREQUISITE] 'gcc-aarch64-linux-gnu' already installed.";
  fi

  # libgpiod2
  LIBGPIOD_DEV_INSTALLED=$(dpkg-query -W --showformat='${Status}\n' libgpiod-dev)
  if [[ "$?" == "1" ]] ; then
    echo " [PREREQUISITE] installing 'libgpiod-dev'...";
    sudo apt-get --force-yes --yes install libgpiod-dev
  else
    echo " [PREREQUISITE] 'libgpiod-dev' already installed.";
  fi

  # libgpiod-dev
  LIBGPIOD_2_INSTALLED=$(dpkg-query -W --showformat='${Status}\n' libgpiod-dev)
  if [[ "$?" == "1" ]] ; then
    echo " [PREREQUISITE] installing 'libgpiod-dev'...";
    sudo apt-get --force-yes --yes install libgpiod-dev
  else
    echo " [PREREQUISITE] 'libgpiod-dev' already installed.";
  fi

  # gpiod
  GPIOD_INSTALLED=$(dpkg-query -W --showformat='${Status}\n' gpiod)
  if [[ "$?" == "1" ]] ; then
    echo " [PREREQUISITE] installing 'gpiod'...";
    sudo apt-get --force-yes --yes install gpiod
  else
    echo " [PREREQUISITE] 'gpiod' already installed.";
  fi

  # autoconf
  LIBGPIOD_DEV_INSTALLED=$(dpkg-query -W --showformat='${Status}\n' autoconf)
  if [[ "$?" == "1" ]] ; then
    echo " [PREREQUISITE] installing 'autoconf'...";
    sudo apt-get --force-yes --yes install autoconf
  else
    echo " [PREREQUISITE] 'autoconf' already installed.";
  fi

  # pkg-config
  PKG_CONFIG_INSTALLED=$(dpkg-query -W --showformat='${Status}\n' pkg-config)
  if [[ "$?" == "1" ]] ; then
    echo " [PREREQUISITE] installing 'pkg-config'...";
    sudo apt-get --force-yes --yes install pkg-config
  else
    echo " [PREREQUISITE] 'pkg-config' already installed.";
  fi

  # libtool
  LIBTOOL_INSTALLED=$(dpkg-query -W --showformat='${Status}\n' libtool)
  if [[ "$?" == "1" ]] ; then
    echo " [PREREQUISITE] installing 'libtool'...";
    sudo apt-get --force-yes --yes install libtool
  else
    echo " [PREREQUISITE] 'libtool' already installed.";
  fi

  # autoconf
  AUTOCONF_ARCHIVE_INSTALLED=$(dpkg-query -W --showformat='${Status}\n' autoconf-archive)
  if [[ "$?" == "1" ]] ; then
    echo " [PREREQUISITE] installing 'autoconf-archive'...";
    sudo apt-get --force-yes --yes install autoconf-archive
  else
    echo " [PREREQUISITE] 'autoconf-archive' already installed.";
  fi
fi