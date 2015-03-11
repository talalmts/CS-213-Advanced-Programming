#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>

int main(void)
{
    int listenfd = 0;
    int connfd = 0;
    struct sockaddr_in serv_addr;
    char sendBuff[1025];
    int numrv;

    listenfd = socket(AF_INET, SOCK_STREAM, 0);


    memset(&serv_addr, '0', sizeof(serv_addr));
    memset(sendBuff, '0', sizeof(sendBuff));

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_addr.sin_port = htons(55556);


    printf("---Server Started Listening---\n");
    printf("--Listening on : 127.0.0.1:55556--\n");

    bind(listenfd, (struct sockaddr*)&serv_addr,sizeof(serv_addr));

    if(listen(listenfd, 10) == -1)
    {
        printf("Failed to listen\n");
        return -1;
    }


    while(1)
    {
        connfd = accept(listenfd, (struct sockaddr*)NULL ,NULL);

        printf("--File to be Sent : SendFile.txt. File Size : 23Bytes--\n");
        FILE *fp = fopen("SendFile.txt","rb");
        if(fp==NULL)
        {
            printf("File opern error");
            return 1;   
        }   
       fseek(fp, 0, SEEK_END); // seek to end of file
        int size = ftell(fp); // get current file pointer
        fseek(fp, 0, SEEK_SET); // seek back to beginning of file
        printf("the file's length is %1dB\n",size);


        while(1)
        {
            //bytes read from the file descriptor
            unsigned char buff[256]={0};
            int nread = fread(buff,1,256,fp);
            printf("Bytes read %d \n", nread);        

            
            if(nread > 0)
            {
                printf("Sending.... \n");
                write(connfd, buff, nread);
            }

    
            if (nread < 256)
            {
                if (feof(fp))
                    printf("End of file\n");
                if (ferror(fp))
                    printf("Error reading\n");
                break;
            }


        }
        //donot close after one connection
        close(connfd);
        sleep(1);
    }


    return 0;
}
