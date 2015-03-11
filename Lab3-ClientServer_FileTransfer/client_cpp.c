#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <arpa/inet.h>
    
    //size of the file
    int size;

int client(int port){
    //byte counter
    int bytesReceived=0;
    int sockfd = 0;
    char Buffer[256];
    struct sockaddr_in serv_addr;

    if((sockfd = socket(AF_INET, SOCK_STREAM, 0))< 0)
    {
        printf("\n Error : Could not create socket \n");
        return 1;
    }

 
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(port);
    serv_addr.sin_addr.s_addr = inet_addr("127.0.0.1");


    if(connect(sockfd, (struct sockaddr *)&serv_addr, sizeof(serv_addr))<0)
    {
        printf("\n Error : Connect Failed \n");
        return 1;
    }


    FILE *fp;
    fp = fopen("TransferFile_CPP.txt", "w"); 


    while((bytesReceived = read(sockfd, Buffer, 256)) > 0)
    {
        printf("Bytes received %d\n",bytesReceived);    
        size =bytesReceived;
        fwrite(Buffer, 1,bytesReceived,fp);
        char Buffer1[bytesReceived];

    }


    return bytesReceived;
}
//Unit test to test the file size which is being sent to the client
 void UnitTest(int port){
    printf("-----File Sending from Server to Client----\n");
    printf("----Unit Test for File Size----\n");
    int ret = client(port);
    //printf("ret : %d\n", size);

    
    //known value of file size
    if(size == 23){
        printf("----Test Sucessful---\n");
    }
    else if(ret == 1) {
        printf("---Error establishing connection---\n");
    }
    else{
        printf("---Test Failed---\n");
    }


 }

int main(void)
{
    //for C Server
    UnitTest(55556);

    //for Java Server
   // UnitTest(55555);

    return 0;
}
