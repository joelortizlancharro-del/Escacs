import java.util.Scanner;

public class escacs {

    char[][] taulell;
    int torn = 0;
    Scanner escaner = new Scanner(System.in);
    public static void main(String[] args) {
         escacs p = new escacs();
         p.principal();
      }

  public void principal(){
    boolean movimentIlegal = false;
    String jugadorBlanc;
    String jugadorNegre;
    
    taulell = new char[8][8];
    String[] jugadors = new String[2];

    taulell = crearTaulell(taulell);

    jugadors = creacioDeJugadors(jugadors);
  
  while(taulell[7][4] == 'K'){
    controlTorns();
     jugar(jugadors, taulell);
      mostrarTaulell(taulell);
  }
  
      mostrarTaulell(taulell);

  }
 
  public char[][] crearTaulell(char[][] taulell){
     
    taulell[0][0] = 't';
    taulell[0][1] = 'c';
    taulell[0][2] = 'a';
    taulell[0][3] = 'q';
    taulell[0][4] = 'k';
    taulell[0][5] = 'a';
    taulell[0][6] = 'c';
    taulell[0][7] = 't';

    taulell[7][0] = 'T';
    taulell[7][1] = 'C';
    taulell[7][2] = 'A';
    taulell[7][3] = 'Q';
    taulell[7][4] = 'K';
    taulell[7][5] = 'A';
    taulell[7][6] = 'C';
    taulell[7][7] = 'T';

    for(int i = 0; i < 8; i++){
        for(int j=0; j < 8; j++){
            if(i == 1){
                taulell[i][j] = 'p';
            }
            if(i == 6){
                taulell[i][j] = 'P';
            }
        }
    }
      for(int f = 0; f < 8; f++){ //pot ser passar a mostrar taulell per actualitzar
        for(int c = 0; c < 8; c++){
         if(taulell[f][c] != 't' && taulell[f][c] != 'T' && taulell[f][c] != 'c' && taulell[f][c] != 'C' && taulell[f][c] != 'a' && taulell[f][c] != 'A' && taulell[f][c] != 'k' && taulell[f][c] != 'K' && taulell[f][c] != 'q' && taulell[f][c] != 'Q' && taulell[f][c] != 'p' && taulell[f][c] != 'P'){
         taulell[f][c] = '.';
               }
   
         }
     }

     return taulell;
}

  public void mostrarTaulell(char[][] taulell){
    int contarFiles = 1;
    System.out.println("------------------------");
    for(int f = 0; f < 8; f++){
        for(int c = 0; c < 8; c++){
            System.out.print(taulell[f][c] + "  ");
        }
      System.out.println("|" + contarFiles);
      contarFiles++;
    }
    System.out.println("------------------------");
    System.out.println("a  b  c  d  e  f  g  h");
  }

  public String[] creacioDeJugadors(String[] jugadors){

    System.out.println("Insereix el nom del jugador que controlara les peces blanques: ");
    jugadors[0] = escaner.nextLine();

    System.out.println("Insereix el nom del jugador que controlara les peces negres: ");
    jugadors[1] = escaner.nextLine();
    return jugadors;
  }

  public void jugar(String[] jugadors, char[][] taulell){
   moviment();  
  }

    public void peons(char[][] taulell, int filaOrigen, int columnaOrigen, int filaDesti, int columnaDesti){
     int movimentVerticalPeo = filaOrigen - filaDesti;
     int movimentIlegalNegresPeo = filaDesti - filaOrigen;
     int movimentIlegalBlanquesPeo = filaDesti - filaOrigen;

            if( ((movimentVerticalPeo > 1 || movimentVerticalPeo < -1) && (filaOrigen != 1 && filaOrigen != 6)) || ((filaOrigen != 1 || filaOrigen != 6) && (movimentVerticalPeo > 2 || movimentVerticalPeo < -2)) || ((taulell[filaOrigen][columnaOrigen] == 'p' && movimentIlegalNegresPeo < 0) || (taulell[filaOrigen][columnaOrigen] == 'P' && movimentIlegalBlanquesPeo > 0))){
                System.out.println("MOVIMENT ILEGAL, HAS INTRODUIT UNA FILA INCORRECTE \n Torna a intrduir el moviment ");
                moviment();
            }
           
            
            if( (((columnaDesti != columnaOrigen)) || ((columnaDesti != columnaOrigen) && (columnaOrigen - columnaDesti) > 1) || ((columnaDesti != columnaOrigen) && (columnaOrigen - columnaDesti) < -1)) && ((taulell[filaOrigen][columnaOrigen] == 'p' && taulell[filaDesti][columnaDesti] == '.') || (taulell[filaOrigen][columnaOrigen] == 'P' && taulell[filaDesti][columnaDesti] == '.'))){ //completar cuando hayan varios movimentos
               System.out.println("MOVIMENT ILEGAL, HAS INTRODUIT UNA FILA INCORRECTE \n Torna a intrduir el moviment ");
                moviment();
              
            }

              retornTaulell(taulell,  columnaOrigen,  columnaDesti,  filaDesti,  filaOrigen);
         
            
    
  }
    
   public char[][] retornTaulell(char[][] taulell, int columnaOrigen, int columnaDesti, int filaDesti, int filaOrigen){
       taulell[filaDesti][columnaDesti] = taulell[filaOrigen][columnaOrigen] ;
      taulell[filaOrigen][columnaOrigen]='.';

      return taulell;
   }

   public void moviment(){
    String movimentPeces;
      
    System.out.println("Quina es la peça que vols moure? Ex: e2 e4");
    movimentPeces = escaner.nextLine();
    
    String[] parts = movimentPeces.trim().split("\\s+");
      while (parts.length != 2) {
        if(parts.length != 2){
          System.out.println("MOVIMENT ILEGAL \nTorna a introduir les coordenades.");
          movimentPeces = escaner.nextLine();
          parts = movimentPeces.trim().split("\\s+");
        }
      }
   
   int columnaOrigen = parts[0].charAt(0) - 'a';
    int filaOrigen = parts[0].charAt(1) - '1';

    int columnaDesti = parts[1].charAt(0) - 'a';
    int filaDesti = parts[1].charAt(1) - '1';
     
    while ((columnaOrigen < 0 || columnaOrigen > 7) || (columnaDesti < 0 || columnaDesti > 7) || (filaOrigen < 0 || filaOrigen > 7) || (filaDesti < 0 || filaDesti > 7)){
        if((columnaOrigen < 0 || columnaOrigen > 7) || (columnaDesti < 0 || columnaDesti > 7) || (filaOrigen < 0 || filaOrigen > 7) || (filaDesti < 0 || filaDesti > 7)){
                System.out.println("MOVIMENT ILEGAL \nTorna a introduir les coordenades.");
                movimentPeces = escaner.nextLine();
                parts = movimentPeces.trim().split("\\s+");
                columnaOrigen = parts[0].charAt(0) - 'a';
                filaOrigen = parts[0].charAt(1) - '1';

                columnaDesti = parts[1].charAt(0) - 'a';
                filaDesti = parts[1].charAt(1) - '1';
          }
      }
   
    escollirMoviment(taulell, columnaOrigen, columnaDesti, filaDesti, filaOrigen);

   }
  
  public void escollirMoviment(char[][] taulell, int columnaOrigen, int columnaDesti, int filaDesti, int filaOrigen){
        if(taulell[filaOrigen][columnaOrigen] == 'P' || taulell[filaOrigen][columnaOrigen] == 'p'){
        peons(taulell, filaOrigen, columnaOrigen, filaDesti, columnaDesti);
    }
  }
  
 public void controlTorns(){
    if(torn == 0){
    System.out.println("Torn de les blanques.");
    torn = 1;
  }
  else if(torn == 1){
    System.out.println("Torn de les negres.");
    torn = 0;
  }
 }
}

 