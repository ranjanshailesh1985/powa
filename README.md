# powa
Loganalyzer

The system is working on a recorded activity log files and the
application process these logs to identify any suspicious activity.

The implementation takes one line of the log file at a time and return
the IP address if any suspicious activity is detected or null if the
activity seems to be normal.

The detection method will be to identify a single IP address that has
attempted a failed login 5 or more times within a 5 minute period in
which case the suspicious IP has to be returned.

Main Class: com.powa.loganalyzer.core.i.Detector
