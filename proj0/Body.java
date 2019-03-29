public class Body{
  double xxPos;
  double yyPos;
  double xxVel;
  double yyVel;
  double mass;
  String imgFileName;
  public static final double G = 6.67E-11;

  public Body(double xP, double yP, double xV,
              double yV, double m, String img){
       xxPos = xP;
       yyPos = yP;
       xxVel = xV;
       yyVel = yV;
       mass = m;
       imgFileName = img;
  }

  public Body(Body b){
    Body bCopy = b;
    xxPos = 1.0;
    yyPos = 2.0;
    xxVel = 3.0;
    yyVel = 4.0;
    mass = 5.0;
    imgFileName = "jupiter.gif";
  }

  public double calcDistance(Body b){
    return Math.sqrt((this.xxPos - b.xxPos)*(this.xxPos - b.xxPos) + (this.yyPos - b.yyPos)*(this.yyPos - b.yyPos));
  }

  public double calcForceExertedBy(Body b){
    double r = this.calcDistance(b);
    return (G * b.mass * this.mass)/(r * r);
  }

  public double calcForceExertedByX(Body b){
    double x = b.xxPos - this.xxPos;
    double r = this.calcDistance(b);
    double F = this.calcForceExertedBy(b);
    return F * (x/r);
  }

  public double calcForceExertedByY(Body b){
    double y = b.yyPos - this.yyPos;
    double r = this.calcDistance(b);
    double F = this.calcForceExertedBy(b);
    return F * (y/r);
  }

  public double calcNetForceExertedByX(Body[] b){
    double sum = 0;
    for(int i = 0; i < b.length; i++){
      if(b[i].equals(this)){
        continue;
      }
      else{
        sum = this.calcForceExertedByX(b[i]) + sum;
      }
    }
    return sum;
  }

  public double calcNetForceExertedByY(Body[] b){
    double sum = 0;
    for(int i = 0; i < b.length; i++){
      if(b[i].equals(this)){
        continue;
      }
      else{
        sum = this.calcForceExertedByY(b[i]) + sum;
      }
    }
    return sum;
  }

  public void update(dt, fX, fY){
    
  }

}
