public class Body{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  private static final double G = 6.67E-11;

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
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
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

  public void update(double dt, double fX, double fY){
    this.xxVel = this.xxVel + dt * fX/this.mass;
    this.xxPos = this.xxPos + dt * this.xxVel;
    this.yyVel = this.yyVel + dt * fY/this.mass;
    this.yyPos = this.yyPos + dt * this.yyVel;
  }

  public void draw(){
    String fullFilename = "/images/" + this.imgFileName;
    StdDraw.picture(this.xxPos, this.yyPos, fullFilename);
  }

}
