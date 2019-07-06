Before using this CloudBciClient, you need first to upgrade the gradle build file by adding
all components in the gradle file of this project.

Then, you have to allow clear text network communication by changing the AndroidManifest.xml
file correspond to such file in this repository.

The way you use this restful client is very simple.
First, you need to instantiate a CloudBciClient with specified URL and port.
Then, for whatever data you have you simply call the function insert_real_time_data
Noted that the insert_real_tine_data function is non-blocking, meaning that you don't
have to worry about the delay caused by executing this function. All the network
communications are running at background.
