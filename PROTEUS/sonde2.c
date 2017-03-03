#include <sonde2.h>
#include <math.h>
#include <LCD.c>

#define CONV_CST 0.48875855327
#define TRESHOLD 25
#define TENS_DISPLAY PIN_E0
#define UNITY_DISPLAY PIN_E1
#define GREEN_LED PIN_C0
#define RED_LED PIN_C1


int simpleBCDConverter(value) {
   // Shift tens from 4 bits to the left
   // So we have ([Tens] [Unity]) XXXX XXXX
   return ((value / 10 % 10 << 4) + value % 10);
}

/**
 * Convert a number of 10 bits to its BCD equivalent
 */
void bitsToBCD(int value) {
   int tens = value / 10 % 10;
   int unity = value % 10;
   
   output_d(tens);
   output_high(TENS_DISPLAY);
   output_low(UNITY_DISPLAY);
   delay_ms(10);
   
   output_d(unity);
   output_low(TENS_DISPLAY);
   output_high(UNITY_DISPLAY);
   
   
   
   delay_ms(10);
}

/**
 * Check temp level, and switch on the right led
 */
void checkLed(int temp) {
   // If temps is greater than treshold
   // Blinking Red LED with 555 (astable)
   if (temp > TRESHOLD) {
      output_low(GREEN_LED);
      output_high(RED_LED);
      printf(3); // alert level => Warning
   } else { // Otherwise, green LED
      output_high(GREEN_LED);
      printf(0); // alert level => Cooling
   }
}

void main()
{
   setup_adc_ports(AN0);
   set_adc_channel(0); // A0 connect� � l'entr�e analogique
   setup_adc(ADC_CLOCK_INTERNAL);
   setup_timer_0(RTCC_INTERNAL|RTCC_DIV_1|RTCC_8_BIT); // 51,2 us overflow
   setup_timer_1(T1_INTERNAL|T1_DIV_BY_1); //13,1 ms overflow
   
   setup_low_volt_detect(FALSE);
   
   int temperature;
   delay_ms(10);
   printf("%d", TRESHOLD); // send treshold on connection with java interface
   
   while(TRUE) {
      //delay_ms(10);
      // Read the value from A/N converter (10bits [0 => 1023])
      // And convert it to a range from 0 to 100 (�C)
      // 0.48 => (5 / 1023) * 100
      temperature = read_adc() * CONV_CST;
      
      // Send temperature to RS232
      printf("%d", temperature);
      // Check temp level
      checkLed(temperature);

      // Convert bits to BCD
      // And show temp on 7 segment displays
      bitsToBCD(temperature);
   }

}
