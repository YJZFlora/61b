public class NBody{

  public static double readRadius(String filename){
    In in = new In(filename);
    double number = 0.0;
    for(int i=0; i<2; i++){
     number = in.readDouble();
    }
    return number;
  }

  public static Body[] readBodies(String filename){
    In in = new In(filename);
    int planetNumber = in.readInt();
    Body[] bodyArray = new Body[planetNumber];
    in.readDouble();
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String img;
    for(int i=0; i<planetNumber;i++){
      xxPos = in.readDouble();
      yyPos = in.readDouble();
      xxVel = in.readDouble();
      yyVel = in.readDouble();
      mass = in.readDouble();
      img = in.readString();
      bodyArray[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, img);
    }
    return bodyArray;
  }

}
