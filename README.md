# OSGI-String-Calculator

OSGI String Calculator is a simple calculator with two input fields and four buttons for the operations. Inputs must be string and the result will also be String.

It composed of two service packages. One package is to initialize the UI components, their listeners, and use the OSGI String Calculator Service package for the conversions.
It supports two languages, Turkish and English. Default language is Turkish. However, if it detects the system language is English, it loads the English user interface.

The conversion limit is INT_MAX.
