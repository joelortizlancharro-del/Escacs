import java.util.ArrayList;
import java.util.Scanner;

public class escacs {
    boolean movimentIlegal = false;
    char[][] taulell;
    int torn = 0;
    int filaOrigen;
    int columnaOrigen;
    int filaDesti;
    int columnaDesti;
    int contador = 0;
    boolean tirarToballola = false;
    String movimentPeces;
  
    Scanner escaner = new Scanner(System.in);
    public static void main(String[] args) {
         escacs p = new escacs();
         p.principal();
      }

  public void principal(){
    String jugadorBlanc;
    String jugadorNegre;
    
    taulell = new char[8][8];
    String[] jugadors = new String[2];
    ArrayList<String> movimentsBlanques = new ArrayList<>();
    ArrayList<String> movimentsNegres = new ArrayList<>();

    taulell = crearTaulell(taulell);

    jugadors = creacioDeJugadors(jugadors);

      mostrarTaulell(taulell);
      contarReis(taulell, jugadors);
  while(contador == 2){
      controlTorns();
      rendirse(taulell, jugadors);
      if(tirarToballola == false && contador == 2){
        
          jugar(jugadors, taulell, movimentsBlanques, movimentsNegres);
          mostrarTaulell(taulell);
          if(movimentIlegal == false){
            if(torn == 0){
            torn = 1;
          }
          else if(torn == 1){
            torn = 0;
          }
          }
          contador = 0;
          contarReis(taulell, jugadors);
        if(contador != 2 && tirarToballola == false){
            System.out.println("La partida ha acabat!!! ");
        if(torn == 0){
            System.out.println("EL GUANYADOR ES:" + jugadors[1]);
        }
        else{
            System.out.println("EL GUANYADOR ES:" + jugadors[0]);
        }
      }
      }
   }
     mostrarMoviments(movimentsBlanques, movimentsNegres);
    //tornarAJugar(jugadors, movimentsBlanques, movimentsNegres);
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

  public void jugar(String[] jugadors, char[][] taulell,ArrayList<String> movimentsBlanques, ArrayList<String> movimentsNegres){
    
   moviment();  
   if(movimentIlegal == false){
     retornTaulell(taulell, columnaOrigen, columnaDesti, filaDesti, filaOrigen);
     imprimirMoviments(movimentsBlanques, movimentsNegres, movimentPeces);
    }

  }

  public boolean peons(char[][] taulell, int filaOrigen, int columnaOrigen, int filaDesti, int columnaDesti){
      movimentIlegal = false;
     int movimentVerticalPeo = filaOrigen - filaDesti;
     int movimentIlegalNegresPeo = filaDesti - filaOrigen;
     int movimentIlegalBlanquesPeo = filaDesti - filaOrigen;

            if( ((movimentVerticalPeo > 1 || movimentVerticalPeo < -1) && (filaOrigen != 1 && filaOrigen != 6)) || ((filaOrigen != 1 || filaOrigen != 6) && (movimentVerticalPeo > 2 || movimentVerticalPeo < -2)) || ((taulell[filaOrigen][columnaOrigen] == 'p' && movimentIlegalNegresPeo < 0) || (taulell[filaOrigen][columnaOrigen] == 'P' && movimentIlegalBlanquesPeo > 0))){
                System.out.println("MOVIMENT ILEGAL!!!");
                movimentIlegal = true;
                return movimentIlegal;
            }
           
            if( (((columnaDesti != columnaOrigen)) || ((columnaDesti != columnaOrigen) && (columnaOrigen - columnaDesti) > 1) || ((columnaDesti != columnaOrigen) && (columnaOrigen - columnaDesti) < -1)) && ((taulell[filaOrigen][columnaOrigen] == 'p' && taulell[filaDesti][columnaDesti] == '.') || (taulell[filaOrigen][columnaOrigen] == 'P' && taulell[filaDesti][columnaDesti] == '.'))){ //completar cuando hayan varios movimentos
               System.out.println("MOVIMENT ILEGAL!!!");
                movimentIlegal = true;
                return movimentIlegal;
            }
            if(columnaDesti == columnaOrigen && filaDesti != filaOrigen && taulell[filaDesti][columnaDesti] != '.'){ //no menjar en vertical
               System.out.println("MOVIMENT ILEGAL!!!");
                movimentIlegal = true;
                return movimentIlegal;
            }
            if(filaOrigen != filaDesti && columnaOrigen != columnaDesti && taulell[filaDesti][columnaDesti] == '.'){ //si es diferent fila i diferent columna no fer moviment
              System.out.println("MOVIMENT ILEGAL!!!");
                movimentIlegal = true;
                return movimentIlegal;
            }
            if( columnaOrigen != columnaDesti && filaDesti == filaOrigen && taulell[filaDesti][columnaDesti] != '.'){ // no menjar en horitzontal
              System.out.println("MOVIMENT ILEGAL!!!");
                movimentIlegal = true;
                return movimentIlegal;
            }
            if(taulell[filaDesti][columnaDesti] != '.' && ((columnaOrigen - columnaDesti) > 1 || (columnaDesti - columnaOrigen) < -1)){ //control que no es poggui menjar mes de una vegada al costst
               System.out.println("MOVIMENT ILEGAL!!!");
                movimentIlegal = true;
                return movimentIlegal;
            }
            if(filaDesti - filaOrigen == 2){ //control de no peces en mig quan fem el primer moviment i son dos
              for(int i = filaOrigen + 1; i < filaDesti; i++){
                if(taulell[i][columnaDesti] != '.'){
                  System.out.println("MOVIMENT ILEGAL!!!");
                movimentIlegal = true;
                return movimentIlegal;
                }
              }
            }
            if(filaDesti - filaOrigen == -2){ //control de no peces en mig quan fem el primer moviment i son dos
              for(int i = filaDesti + 1; i < filaOrigen; i++){
                if(taulell[i][columnaDesti] != '.'){
                  System.out.println("MOVIMENT ILEGAL!!!");
                movimentIlegal = true;
                return movimentIlegal;
                }
              }
            }
             if(taulell[filaOrigen][columnaOrigen] == 'p' && !Character.isUpperCase(taulell[filaDesti][columnaDesti]) && taulell[filaDesti][columnaDesti] != '.'){
                System.out.println("MOVIMENT ILEGAL!!!");
                movimentIlegal = true;
                return movimentIlegal;
              }
              else if(taulell[filaOrigen][columnaOrigen] == 'P' && Character.isUpperCase(taulell[filaDesti][columnaDesti]) && taulell[filaDesti][columnaDesti] != '.'){
                  System.out.println("MOVIMENT ILEGAL!!!");
                  movimentIlegal = true;
                  return movimentIlegal;
              }
            
     return movimentIlegal;
  }
 
  public boolean torre(char[][] taulell){
      movimentIlegal = false;   

          if(filaOrigen != filaDesti && columnaOrigen != columnaDesti){ //mirar que el moviment sigui unicament vertical o horitzontal
            System.out.println("MOVIMENT ILEGAL!!!");
                  movimentIlegal = true;
                  return movimentIlegal;
              }

          if(filaDesti > filaOrigen && columnaDesti == columnaOrigen){ //Moviment vertical positiu
            for(int i = filaOrigen + 1; i < filaDesti; i++){
                  if(taulell[i][columnaOrigen] !='.'){
                      movimentIlegal = true;
                      System.out.println("MOVIMENT ILEGAL!!!");
                      return movimentIlegal;
              }
            }
          }
          if(filaDesti < filaOrigen && columnaDesti == columnaOrigen){ //moviment vertical negatiu
            for(int i = filaDesti + 1; i < filaOrigen; i++){
                  if(taulell[i][columnaOrigen] !='.'){
                      movimentIlegal = true;
                      System.out.println("MOVIMENT ILEGAL!!!");
                      return movimentIlegal;
              }
            }
          }
          if(columnaDesti > columnaOrigen && filaDesti == filaOrigen){ //moviment horitzontal positiu
            for(int i = columnaOrigen + 1; i < columnaDesti; i++){
                  if(taulell[filaDesti][i] !='.'){
                      movimentIlegal = true;
                      System.out.println("MOVIMENT ILEGAL!!!");
                      return movimentIlegal;
              }
            }
          }
          if(columnaDesti < columnaOrigen && filaDesti == filaOrigen){ //moviment horitzontal negatiu
            for(int i = columnaDesti + 1; i < columnaOrigen; i++){
                  if(taulell[filaDesti][i] !='.'){
                      movimentIlegal = true;
                      System.out.println("MOVIMENT ILEGAL!!!");
                      return movimentIlegal;
              }
            }
          }
             if(taulell[filaOrigen][columnaOrigen] == 't' && !Character.isUpperCase(taulell[filaDesti][columnaDesti]) && taulell[filaDesti][columnaDesti] != '.'){
                System.out.println("MOVIMENT ILEGAL!!!");
                movimentIlegal = true;
                return movimentIlegal;
              }
              else if(taulell[filaOrigen][columnaOrigen] == 'T' && Character.isUpperCase(taulell[filaDesti][columnaDesti]) && taulell[filaDesti][columnaDesti] != '.'){
                System.out.println("MOVIMENT ILEGAL!!!");
                
                movimentIlegal = true;
                return movimentIlegal;
              }
             
              return movimentIlegal;
            
            
    }

  public boolean cavall(char[][] taulell){
      movimentIlegal = true;
      int filaMoviment = filaDesti - filaOrigen;
      int columnaMoviment = columnaDesti - columnaOrigen;
      
       if(taulell[filaOrigen][columnaOrigen] == 'c' && !Character.isUpperCase(taulell[filaDesti][columnaDesti]) && taulell[filaDesti][columnaDesti] != '.'){
        System.out.println("MOVIMENT ILEGAL!!!");
        movimentIlegal = true;
        return movimentIlegal;
       }
       else if(taulell[filaOrigen][columnaOrigen] == 'C' && Character.isUpperCase(taulell[filaDesti][columnaDesti]) && taulell[filaDesti][columnaDesti] != '.'){
        System.out.println("MOVIMENT ILEGAL!!!");
        movimentIlegal = true;
        return movimentIlegal;
       }
       
       if((filaMoviment == 1 && (columnaMoviment == 2 || columnaMoviment == -2)) ||
          (filaMoviment == -1 && (columnaMoviment == 2 || columnaMoviment == -2))||
          (filaMoviment == 2 && (columnaMoviment == 1 || columnaMoviment == -1))||
          (filaMoviment == -2 && (columnaMoviment == 1 || columnaMoviment == -1))){
            movimentIlegal = false;
            return movimentIlegal;
       }
       else{
        System.out.println("MOVIMENT ILEGAL!!!");
        movimentIlegal = true;
        return movimentIlegal;
       }
    }

  public boolean alfil(char[][] taulell){
    movimentIlegal = false;
    int movimentVerticalAlfil;
    int movimentHoritzontalAlfil;

   if(filaDesti > filaOrigen){
    movimentVerticalAlfil = 1; 
   }
   else{
    movimentVerticalAlfil = -1;
   }

   if(columnaDesti > columnaOrigen){
      movimentHoritzontalAlfil = 1;
   }
   else{
    movimentHoritzontalAlfil = -1;
   }

   int fil = filaOrigen + movimentVerticalAlfil;
   int col = columnaOrigen + movimentHoritzontalAlfil;

    if(!(filaDesti-filaOrigen == columnaDesti-columnaOrigen || 
      filaDesti-filaOrigen == columnaOrigen-columnaDesti || 
      filaOrigen-filaDesti == columnaOrigen-columnaDesti || 
      filaOrigen-filaDesti == columnaDesti-columnaOrigen)){
      System.out.println("MOVIMENT ILEGAL!!!");
        movimentIlegal = true;
        return movimentIlegal;
    }

     if(taulell[filaOrigen][columnaOrigen] == 'a' && !Character.isUpperCase(taulell[filaDesti][columnaDesti]) && taulell[filaDesti][columnaDesti] != '.'){
                System.out.println("MOVIMENT ILEGAL!!!");
                movimentIlegal = true;
                return movimentIlegal;
              }
              else if(taulell[filaOrigen][columnaOrigen] == 'A' && Character.isUpperCase(taulell[filaDesti][columnaDesti]) && taulell[filaDesti][columnaDesti] != '.'){
                  System.out.println("MOVIMENT ILEGAL!!!");
                  movimentIlegal = true;
                  return movimentIlegal;
              }
        //cambiar aixo
         while(fil != filaDesti && col != columnaDesti){
          if(taulell[fil][col] != '.'){
            System.out.println("MOVIMENT ILEGAL!!!");
                movimentIlegal = true;
                return movimentIlegal;
          }
          fil = fil+movimentVerticalAlfil;
          col = col+movimentHoritzontalAlfil;
         }

             
    return movimentIlegal;
  }

  public boolean reina(char[][] taulell){
        
    if(taulell[filaOrigen][columnaOrigen] == 'q' && !Character.isUpperCase(taulell[filaDesti][columnaDesti]) && taulell[filaDesti][columnaDesti] != '.'){
                System.out.println("MOVIMENT ILEGAL!!!");
                movimentIlegal = true;
                return movimentIlegal;
              }
              else if(taulell[filaOrigen][columnaOrigen] == 'Q' && Character.isUpperCase(taulell[filaDesti][columnaDesti]) && taulell[filaDesti][columnaDesti] != '.'){
                  System.out.println("MOVIMENT ILEGAL!!!");
                  movimentIlegal = true;
                  return movimentIlegal;
              }

       return alfil(taulell) && torre(taulell);
  }

  public boolean rei(char[][] taulell){
    movimentIlegal = false;

    if(filaDesti - filaOrigen < 1 ||
      filaDesti - filaOrigen > -1  ||
      columnaDesti - columnaOrigen > 1 || 
      columnaDesti - columnaOrigen < -1){
         System.out.println("MOVIMENT ILEGAL!!!");
         movimentIlegal = true;
         return movimentIlegal;
      }

      if(taulell[filaOrigen][columnaOrigen] == 'k' && !Character.isUpperCase(taulell[filaDesti][columnaDesti]) && taulell[filaDesti][columnaDesti] != '.'){
                System.out.println("MOVIMENT ILEGAL!!!");
                movimentIlegal = true;
                return movimentIlegal;
              }
              else if(taulell[filaOrigen][columnaOrigen] == 'K' && Character.isUpperCase(taulell[filaDesti][columnaDesti]) && taulell[filaDesti][columnaDesti] != '.'){
                  System.out.println("MOVIMENT ILEGAL!!!");
                  movimentIlegal = true;
                  return movimentIlegal;
              }
      return movimentIlegal;
  }

  public char[][] retornTaulell(char[][] taulell, int columnaOrigen, int columnaDesti, int filaDesti, int filaOrigen){
       taulell[filaDesti][columnaDesti] = taulell[filaOrigen][columnaOrigen] ;
      taulell[filaOrigen][columnaOrigen]='.';

      return taulell;
   }

  public void moviment(){
      escaner.nextLine();
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
   
    columnaOrigen = parts[0].charAt(0) - 'a';
    filaOrigen = parts[0].charAt(1) - '1';

    columnaDesti = parts[1].charAt(0) - 'a';
    filaDesti = parts[1].charAt(1) - '1';
     
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
    controlsBasics(taulell);
    if(movimentIlegal == false){
      escollirMoviment(taulell, columnaOrigen, columnaDesti, filaDesti, filaOrigen);
    }
    

   }
  
  public void escollirMoviment(char[][] taulell, int columnaOrigen, int columnaDesti, int filaDesti, int filaOrigen){
    if(taulell[filaOrigen][columnaOrigen] == 'P' || taulell[filaOrigen][columnaOrigen] == 'p'){
      peons(taulell, filaOrigen, columnaOrigen, filaDesti, columnaDesti);
    }
    else if(taulell[filaOrigen][columnaOrigen] == 'T' || taulell[filaOrigen][columnaOrigen] == 't'){
      torre(taulell);
    }
    else if(taulell[filaOrigen][columnaOrigen] == 'C' || taulell[filaOrigen][columnaOrigen] == 'c'){
      cavall(taulell);
    }
    else if(taulell[filaOrigen][columnaOrigen] == 'A' || taulell[filaOrigen][columnaOrigen] == 'a'){
      alfil(taulell);
    }
    else if(taulell[filaOrigen][columnaOrigen] == 'Q' || taulell[filaOrigen][columnaOrigen] == 'q'){
      reina(taulell);
    }
    else if(taulell[filaOrigen][columnaOrigen] == 'K' || taulell[filaOrigen][columnaOrigen] == 'k'){
      rei(taulell);
    }
  }
  
  public void controlTorns(){
      if(torn == 0){
      System.out.println("Torn de les blanques.");
    }
    else if(torn == 1){
      System.out.println("Torn de les negres.");
    }
 }

 public boolean controlsBasics(char[][] taulell){
  movimentIlegal = false;
  if((taulell[filaOrigen][columnaOrigen] == 'p' || taulell[filaOrigen][columnaOrigen] == 't' || taulell[filaOrigen][columnaOrigen] == 'c' || taulell[filaOrigen][columnaOrigen] == 'a' || taulell[filaOrigen][columnaOrigen] == 'q' || taulell[filaOrigen][columnaOrigen] == 'k') && torn == 0){
    System.out.println("NOMES POTS MOURE PECES DEL TEU COLOR!!!");
    movimentIlegal = true;
    return movimentIlegal;
  }
  if((taulell[filaOrigen][columnaOrigen] == 'P' || taulell[filaOrigen][columnaOrigen] == 'T' || taulell[filaOrigen][columnaOrigen] == 'C' || taulell[filaOrigen][columnaOrigen] == 'A' || taulell[filaOrigen][columnaOrigen] == 'Q' || taulell[filaOrigen][columnaOrigen] == 'K') && torn == 1){
    System.out.println("NOMES POTS MOURE PECES DEL TEU COLOR!!!");
    movimentIlegal = true;
    return movimentIlegal;
  }
  if(taulell[filaOrigen][columnaOrigen] == taulell[filaDesti][columnaDesti]){
    System.out.println("NO POTS MOURE LA PEÇA AL MATEIX LLOC ON ESTAVA!!!");
    movimentIlegal = true;
    return movimentIlegal;
  }
  if(taulell[filaOrigen][columnaOrigen] == '.'){
    System.out.println("NO HAS SELECCIONAT CAP PEÇA!!!");
    movimentIlegal = true;
    return movimentIlegal;
  }
  return movimentIlegal;
 }

 public void contarReis(char[][] taulell, String[] jugadors){
   for(int i = 0; i < 8; i++){
    for(int j = 0; j < 8; j++){
      if(taulell[i][j] == 'k' || taulell[i][j] == 'K'){
        contador ++;
      }
    }
   }
 }
   
 public boolean rendirse(char[][] taulell, String[] jugadors){
  int rendicio;
  System.out.println("Vols rendirte? \n1.No \n2.Si ");
  rendicio = controlErrorsInt();
  while(rendicio != 1 && rendicio != 2){
    System.out.println("Ha de ser el numero 1 o el 2.");
    rendicio = controlErrorsInt();
  }
  if(rendicio == 1){
    tirarToballola = false;
  }
  else{
    tirarToballola = true;
    if(torn == 0){
      for(int i = 0; i < 8; i++){
        for(int j= 0; j < 8; j++){
          if(taulell[i][j] == 'K'){
            taulell[i][j] = '.';
            System.out.println("EL GUANYADOR ES:" + jugadors[1]);
            contarReis(taulell, jugadors);
            return tirarToballola;
          }
        }
      }
    }
    else if(torn == 1){
      tirarToballola = true;
      for(int i = 0; i < 8; i++){
        for(int j= 0; j < 8; j++){
          if(taulell[i][j] == 'k'){
            taulell[i][j] = '.';
            System.out.println("EL GUANYADOR ES:" + jugadors[0]);
            contarReis(taulell, jugadors);
            return tirarToballola;
          }
        }
      }
    }
  }
  return tirarToballola;
 }

 public void imprimirMoviments(ArrayList<String> movimentsBlanques, ArrayList<String> movimentsNegres, String movimentPeces){
   if(torn == 0){
    movimentsBlanques.add(movimentPeces);
   }
   else{
    movimentsNegres.add(movimentPeces);
   }
 }

 public void mostrarMoviments(ArrayList<String> movimentsBlanques, ArrayList<String> movimentsNegres){
  int veureMoviments;
  System.out.println("Vols veure els moviments de la partida? \n1.No \n2.Si");
  veureMoviments = controlErrorsInt();
    while(veureMoviments != 1 && veureMoviments != 2){
    System.out.println("Ha de ser el numero 1 o el 2.");
    veureMoviments = controlErrorsInt();
 }
 if(veureMoviments == 2){
  System.out.println("El moviment de les blanques es: ");
  for(int i = 0; i < movimentsBlanques.size(); i++){
    System.out.println( "Moviment " + (i+1) + ": " + movimentsBlanques.get(i));
  }
  System.out.println("El moviment de les negres es: ");
  for(int i = 0; i < movimentsNegres.size(); i++){
    System.out.println( "Moviment " + (i+1) + ": " + movimentsNegres.get(i));
  }
 }
}

 public void tornarAJugar(String[] jugadors, ArrayList<String> movimentsBlanques, ArrayList<String> movimentsNegres){
  int tornarJoc;
  int mateixosJugadors;
  System.out.println("Voleu tornar a jugar? \n 1.Si \n 2.No");
  tornarJoc = controlErrorsInt();
  while(tornarJoc != 1 && tornarJoc != 2){
    System.out.println("Ha de ser el numero 1 o el 2.");
    tornarJoc = controlErrorsInt();
  }
  if(tornarJoc == 2){
    System.out.println("Espero que us ho hagueu passat be!!! \nFins aviat!!!");
    System.exit(0);
  }
  else if(tornarJoc == 1){
        System.out.println("Sou els mateixos jugadors? \n 1.Si \n 2.No");
        mateixosJugadors = controlErrorsInt();
     while(mateixosJugadors != 1 && mateixosJugadors != 2){
        System.out.println("Ha de ser el numero 1 o el 2.");
        mateixosJugadors = controlErrorsInt();
      }
      if(mateixosJugadors == 2){
        escaner.nextLine();
         creacioDeJugadors(jugadors);
      }
      else if(mateixosJugadors == 1){
        if(torn == 0 && tirarToballola == true){
          jugadors[0] = jugadors[1];
        }
        else if(torn == 1 && tirarToballola == true){
          jugadors[0] = jugadors[0];
        }
        else if(torn == 0 && tirarToballola == false){
          jugadors[0] = jugadors[0];
        }
        else if(torn == 1 && tirarToballola == false){
          jugadors[0] = jugadors[1];
        }
      }
   
   }

 }

 public void retornarAlJoc(String[] jugadors,ArrayList<String> movimentsBlanques, ArrayList<String> movimentsNegres){
   taulell = new char[8][8];
  taulell = crearTaulell(taulell);
        mostrarTaulell(taulell);
        contador = 0;
        tirarToballola = false;
      contarReis(taulell, jugadors);
  while(contador == 2){
      controlTorns();
      rendirse(taulell, jugadors);
      if(contador != 2 && tirarToballola == false){
        System.out.println("La partida ha acabat!!! ");
        if(torn == 0){
            System.out.println("EL GUANYADOR ES:" + jugadors[1]);
        }
        else{
            System.out.println("EL GUANYADOR ES:" + jugadors[0]);
        }
      }
     
      if(tirarToballola == false && contador == 2){
        
          jugar(jugadors, taulell, movimentsBlanques, movimentsNegres);
          mostrarTaulell(taulell);
          if(torn == 0){
            torn = 1;
          }
          else if(torn == 1){
            torn = 0;
          }
          contador = 0;
          contarReis(taulell, jugadors);
      }
   }
    mostrarMoviments(movimentsBlanques, movimentsNegres);
    tornarAJugar(jugadors, movimentsBlanques, movimentsNegres);

 }

 public int controlErrorsInt(){
  int numero = 0;
  while(numero == 0){
     try{
        numero = escaner.nextInt();
      }
      catch (Exception InputMismatchException) {
        System.out.println("Ha de ser un numero!!!");
        numero = 0;
        escaner.nextLine();
      }
    }
    return numero;
  }
}
 