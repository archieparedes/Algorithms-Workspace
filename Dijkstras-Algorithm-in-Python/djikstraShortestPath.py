"""
 * Archie Paredes
 * Created November 2, 2018 Finished November 6, 2018
 * Algorithms
 * Djikstras Shortest Path
"""
from collections import defaultdict
import heapq
import math

# this graph class is technically unnecessary
class Graph():
    def __init__(self):
        self.graph = defaultdict(list)

    def addEdge(self, source, dest, weight):
        self.graph[source].append([dest,weight])
        self.graph[dest].append([source, weight])

    def getGraph(self):
        return (self.graph) # returns a dictionary {source:[destination,weight]}
    
    def vertices(self):
        return (list(self.graph.keys())) # returns keys

    def adj(self,source):
        return (list(self.graph[source])) # returns values of a key[source]
    
    
def dijkstra(adj, start, predecessor):
    heap = [] # empty heap list
    key = list() # this will store distance from start
    
    for i in range(0,n): # initialize
        key.append(math.inf)
        predecessor.append(None)

    key[list(adj).index(start)] = 0
    predecessor[list(adj).index(start)] = None
    
    heapq.heappush(heap, (list(adj)[0], 0)) # adds first vertices in heap
    
    for v in list(adj.keys()): # add all vertices and weight
        if(v == start):
            pass
        else:
            heapq.heappush(heap, (v, key[list(adj).index(v)])) 
            
    heapq.heapify(heap) 

    while(len(heap) != 0):
        u = heapq.heappop(heap) # extract_min from heap
        uKey = u[0][0] # key of u
        uIndex = list(adj).index(uKey) # index of u from adj
        for w, weight in list(adj[uKey]):
            wIndex = list(adj).index(w) # gets index from adj
            if (key[uIndex] + weight < key[wIndex]):
                val = key[uIndex] + weight # replace inf
                key[wIndex] = val
                predecessor[wIndex] = uKey # stores uKey as predecessor of w
                if(len(heap) != 0):
                    heapq.heappush(heap, (w, int(val))) # updates value of key w 
   
    print('From A to B: ', key[list(adj).index('B')])

    pathToB = []
    ind = 'B'
    pathToB.append(ind)
    
    while(ind != 'A'):
        ind = predecessor[list(adj).index(ind)]
        pathToB.append(ind)
        
    pathToB.reverse()
    print(pathToB)

fileName = str(input("Enter file name: "))
infile = open(fileName, 'r')
readlines = infile.readlines()
line = list()
n = int(readlines[0]) # amount of vertices
graph = Graph()

for i in range(1, len(readlines)):
    for j in readlines[i].split():
        line.append(j)
    graph.addEdge(line[0], line[1], int(line[2]))
    line = [] # clear

adj = graph.getGraph()
start = list(adj)[0]
predecessor = []
    
dijkstra(adj, start, predecessor)

"""
Case1: PASS
Case2: PASS
Case3: PASS
"""
