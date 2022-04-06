int upShift = 0;
int downShift = 0;
bool up = false;
bool down = false;
void setup() {
  // put your setup code here, to run once:
  pinMode(2, INPUT);
  pinMode(3, INPUT);

  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  upShift = digitalRead(3);
  downShift = digitalRead(2);
  if(upShift == LOW && up) {
    //Serial.print("u");
    up = false;
    Serial.print("u");
  }else if(not up and upShift == HIGH){
    Serial.print("U");
    up = true;
  }

  if(downShift == LOW && down) {
    //Serial.print("d");
    down = false;
    Serial.print("d");
  }else if(not down and downShift == HIGH){
    Serial.print("D");
    down = true;
  }
  delay(10);
}
