#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>   

typedef struct
{
   int last; //마지막 idx
   int size; //현재 노드 개수
   int max_size; //최대 노드 개수
   int* array;
}HEAP;

HEAP* create_heap(int size)
{
   HEAP* heap = (HEAP*)malloc(sizeof(HEAP));
   heap->last = -1;
   heap->size = 0;
   heap->max_size = size;
   heap->array = (int*)malloc(sizeof(int)*size);
   return heap;
}

void reheapUp(HEAP* heap, int idx)
{
   if (heap->array[idx]>heap->array[(idx-1)/2]) //현재 인덱스의 값 > 부모노드의 값 이면 힙 규정 위배
   {
      int temp = heap->array[idx];
      heap->array[idx] = heap->array[(idx - 1) / 2];
      heap->array[(idx - 1) / 2] = temp;
      reheapUp(heap, (idx - 1) / 2);
   }
}

bool insertHeap(HEAP* heap, int data)
{
   if (heap->size == heap->max_size)
      return false;
   else
   {
      heap->size++;
      heap->last++;
      heap->array[heap->last] = data;
      reheapUp(heap, heap->last);
      return true;
   }
}
void reheapDown(HEAP* heap,int idx)
{   
   if (heap->array[idx * 2 + 1] < heap->array[idx * 2 + 2])
   {
      if (heap->array[idx] < heap->array[idx * 2 + 2] && (idx * 2 + 2) <= heap->last)
      {
         int temp = heap->array[idx];
         heap->array[idx] = heap->array[idx * 2 + 2];
         heap->array[idx * 2 + 2] = temp;
         reheapDown(heap, idx * 2 + 2);
      }
   }
   if (heap->array[idx * 2 + 1] > heap->array[idx * 2 + 2])
   {
      if (heap->array[idx] < heap->array[idx * 2 + 1] && (idx * 2 + 1) <= heap->last)
      {
         int temp = heap->array[idx];
         heap->array[idx] = heap->array[idx * 2 + 1];
         heap->array[idx * 2 + 1] = temp;
         reheapDown(heap, idx * 2 + 1);
      }
   }
   
}

int deleteHeap(HEAP* heap)
{
   if (heap->size == 0)
      return false;
   else
   {
      int temp = heap->array[0];
      heap->array[0] = heap->array[heap->last];
      heap->last--;
      heap->size--;
      reheapDown(heap, 0);
      return temp;
   }
}


int main(void)
{
   HEAP* h1 = create_heap(6);
   insertHeap(h1, 3);
   insertHeap(h1, 1);
   insertHeap(h1, 4);
   insertHeap(h1, 5);
   insertHeap(h1, 2);
   insertHeap(h1, 6);
   for (int i = 0; i < h1->size; i++)
      printf("%d ", h1->array[i]);
   printf("\n");
   printf("%d ", deleteHeap(h1));
   printf("%d ", deleteHeap(h1));
   printf("%d ", deleteHeap(h1));
   printf("%d ", deleteHeap(h1));
   printf("%d ", deleteHeap(h1));
   printf("%d ", deleteHeap(h1));
   return 0;
   
