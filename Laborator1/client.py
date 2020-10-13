import requests
import sys
import time
import threading

servlets={"sync":"words_text","async":"words_text_async"}
def make_request(letters,request_count,serv):
    start_time=time.time()
    url_addr="http://localhost:5555/Lab_1_war/{}?letters={}".format(serv,letters)
    results=[]
    for request_index in range(0,request_count):
        response=requests.get(url_addr)
        results.append(response)
    execution_time=time.time()-start_time
    return results,execution_time

def append_results_to_list(url,list):
    list.append(requests.get(url))

def make_request_threads(letters,request_count,serv):
    start_time=time.time()
    url_addr="http://localhost:5555/Lab_1_war/{}?letters={}".format(serv,letters)
    results=[]
    threads=[]
    for request_index in range(0,request_count):
        thread=threading.Thread(target=append_results_to_list,args=(url_addr,results))
        threads.append(thread)
        thread.start()
    for thread in threads:
        thread.join()
    execution_time=time.time()-start_time
    return results,execution_time

print("Servlet type : {}".format(sys.argv[3]))
requests_number=int(sys.argv[2])
results,time_elapsed=make_request(sys.argv[1],requests_number,servlets[sys.argv[3]])
print(results[0])
print("One thread requests time for {} requests : {}".format(requests_number,time_elapsed))
results,time_elapsed=make_request_threads(sys.argv[1],requests_number,servlets[sys.argv[3]])
print("Multi thread requests time for {} requests : {}".format(requests_number,time_elapsed))


