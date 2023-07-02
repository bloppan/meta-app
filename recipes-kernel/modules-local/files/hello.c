/*
 * hello.c - The simplest and dirty kernel module
 */

#include <linux/module.h>
#include <linux/kernel.h>

int init_module(void)
{
  printk(KERN_INFO "Hello world\n");
  
  return 0;
}

void cleanup_module(void)
{
  printk(KERN_INFO "Goodbye world\n");
}
