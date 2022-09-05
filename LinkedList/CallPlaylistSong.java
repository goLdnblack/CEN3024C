class PlaylistSong {
   private String name;
   private PlaylistSong nextPlaylistSongPtr;

   PlaylistSong() {
      this.name = "";
      nextPlaylistSongPtr = null;
   }

   PlaylistSong(String name) {
      this.name = name;
      this.nextPlaylistSongPtr = null;
   }

   PlaylistSong(String name, PlaylistSong nextLoc) {
      this.name = name;
      this.nextPlaylistSongPtr = nextLoc;
   }

   void InsertAfter(PlaylistSong nodeLoc) {
      PlaylistSong tmpNext = null;

      tmpNext = this.nextPlaylistSongPtr;
      this.nextPlaylistSongPtr = nodeLoc;
      nodeLoc.nextPlaylistSongPtr = tmpNext;
   }

   PlaylistSong GetNext() {
      return this.nextPlaylistSongPtr;
   }

   void PrintNodeData() {
      System.out.println(this.name);
   }
}

public class CallPlaylistSong {
   public static void main(String[] args) {
      PlaylistSong headObj = null;
      PlaylistSong firstSong = null;
      PlaylistSong secondSong = null;
      PlaylistSong thirdSong = null;
      PlaylistSong currObj = null;

      headObj = new PlaylistSong("head");

      firstSong = new PlaylistSong("Pavanne");
      headObj.InsertAfter(firstSong);

      secondSong = new PlaylistSong("Cavatina");
      firstSong.InsertAfter(secondSong);

      thirdSong = new PlaylistSong("Adagio");
      secondSong.InsertAfter(thirdSong);

      currObj = headObj;

      while (currObj != null) {
         currObj.PrintNodeData();
         currObj = currObj.GetNext();
      }
   }
}