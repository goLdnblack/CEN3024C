class IntNode {
   private int value;
   private IntNode nextIntNodePtr;

   IntNode() {
      this.value = 0;
      this.nextIntNodePtr = null;
   }

   IntNode(int val) {
      this.value = val;
      this.nextIntNodePtr = null;
   }

   IntNode(int val, IntNode nextLoc) {
      this.value = val;
      this.nextIntNodePtr = nextLoc;
   }

   void InsertAfter(IntNode nodeLoc) {
      IntNode tmpNext = null;

      tmpNext = this.nextIntNodePtr;
      this.nextIntNodePtr = nodeLoc;
      nodeLoc.nextIntNodePtr = tmpNext;
   }

   int GetValue() {
      return this.value;
   }

   IntNode GetNext() {
      return this.nextIntNodePtr;
   }

   void PrintData() {
      System.out.println(this.value);
   }
}

public class CallIntNode {
   public static void main(String[] args) {
      IntNode headObj = null;
      IntNode node1 = null;
      IntNode node2 = null;
      IntNode node3 = null;
      IntNode node4 = null;
      IntNode currObj = null;

      headObj = new IntNode(-1);

      node1 = new IntNode(1);
      headObj.InsertAfter(node1);

      node2 = new IntNode(2);
      headObj.InsertAfter(node2);

      node3 = new IntNode(3);
      node1.InsertAfter(node3);

      node4 = new IntNode(4);
      node3.InsertAfter(node4);

      currObj = headObj;

      while (currObj != null) {
         currObj.PrintData();
         currObj = currObj.GetNext();
      }
   }
}