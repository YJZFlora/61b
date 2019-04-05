public class NBody{

  public static void main(String[] args){
    try{
      double T = Double.parseDouble(args[0]);
      double dt = Double.parseDouble(args[1]);
      int refresh = (int)dt;
      String filename = args[2];
      double radius = readRadius(filename);
      Body[] bodies = readBodies(filename);

      StdDraw.enableDoubleBuffering();

      // paint canvas //
      StdDraw.setScale(-radius, radius);
      StdDraw.picture(0, 0, "/images/starfield.jpg");

      StdDraw.show();

      // paint bodies one by one //
      for(int i = 0; i < bodies.length; i++){
        bodies[i].draw();
        StdDraw.show();
      }

      // animation //
        for(int time=0; time < T;){ // refresh each millisecond to make animation //
          StdDraw.clear();
          double[] xForces = new double[bodies.length];
          double[] yForces = new double[bodies.length];
          for(int i = 0; i < bodies.length; i++){ // calculate force for each body //
            xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
            yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
          }
          for(int i = 0; i < bodies.length; i++){ // calculate force for each body //
            bodies[i].update(dt, xForces[i], yForces[i]);
          }
          StdDraw.picture(radius/2, radius/2, "/images/starfield.jpg");

          for(int i = 0; i < bodies.length; i++){
            bodies[i].draw();
          }
          StdDraw.show();
          StdDraw.pause(10);

          time = time + refresh ;

      }


    } catch (Exception ex){
      ex.printStackTrace();;
    }
  }


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
