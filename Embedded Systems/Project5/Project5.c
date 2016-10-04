/*
 * Project4.c
 *
 * Created: 5/13/2015 3:23:43 PM
 *  Author: Ce Hui Li
 */ 


#include <avr/io.h>
#include "avr.h"
#include "lcd.h"
#include <stdio.h>
#include <util/delay.h>
#include <stdbool.h>

float current;
bool running;
bool edit_mode;
bool tripped;
bool start, finish;
bool time_run = false;
int hr, min, sec;
int start_hr, start_min, start_sec;
int end_hr, end_min, end_sec;
int pos = 0;

void init_state()
{
	running = false;
	edit_mode = false;
	tripped = false;
	hr = 0;
	min = 0;
	sec = 0;
	
}


void init_adc()
{
	ADMUX = (1 << REFS0);
	ADCSRA = (1<<ADEN)|(1<<ADPS2)|(1<<ADPS1)|(1<<ADPS0);
}

unsigned short get_sample()
{
	uint8_t c = 0;
	c &= 0b00000111;
	ADMUX = (ADMUX & 0xF8)|c;
	
	ADCSRA |= (1<<ADSC);
	while(ADCSRA & (1<<ADSC));
	return ADC;
}

void init_keypad()
{
	for(int row = 0; row < 4; ++row)
	{
		wait_avr(1);
		SET_BIT(DDRC,row);
		CLR_BIT(PORTC, row);	//strong 0
		wait_avr(1);
		for(int col = 4; col < 8; ++col)
		{
			wait_avr(1);
			CLR_BIT(DDRC, col);
			SET_BIT(PORTC,col);	// weak 1
			wait_avr(1);
		}
	}
}

void update_edit()
{
	edit_mode = (edit_mode) ? false: true;
}

void edit_start()
{
	while(edit_mode && start)
	{
		if (GET_BIT(PINC, 4) == 0)
		{
			if(pos == 0)
			{
				edit_hr_M();
			}
			else if (pos == 3)
			{
				edit_min_M();
			}
			else if(pos == 6)
			{
				edit_sec_M();
			}
			//wait_avr(50);

		}
		if(GET_BIT(PINC, 5) == 0)
		{
			if(pos == 0)
			{
				edit_hr_P();
			}
			else if (pos == 3)
			{
				edit_min_P();
			}
			else if(pos == 6)
			{
				edit_sec_P();
			}
			//wait_avr(50);
		}
		if(GET_BIT(PINC, 7) == 0)
		{
			if (pos < 6)
			{
				pos += 3;
			}
			else
			{
				finish = true;
				start = false;
				pos = 0;
				edit_finish();
			}
		}
		pos_lcd(1, pos);
		puts_lcd2("  ");
		wait_avr(750);
		updateTime1(start_hr, start_min, start_sec);
		pos_lcd(1, 10);
		puts_lcd2("START");
		wait_avr(1000);
	}
	
}

void edit_finish()
{
	wait_avr(1000);
	while(edit_mode && finish)
	{
		if (GET_BIT(PINC, 4) == 0)
		{
			if(pos == 0)
			{
				edit_hr_M();
			}
			else if (pos == 3)
			{
				edit_min_M();
			}
			else if(pos == 6)
			{
				edit_sec_M();
			}
			//wait_avr(50);

		}
		if(GET_BIT(PINC, 5) == 0)
		{
			if(pos == 0)
			{
				edit_hr_P();
			}
			else if (pos == 3)
			{
				edit_min_P();
			}
			else if(pos == 6)
			{
				edit_sec_P();
			}
			//wait_avr(50);
		}
		if(GET_BIT(PINC, 7) == 0)
		{
			if (pos < 6)
			{
				pos += 3;
			}
			else
			{
				update_edit();
				finish = false;
				pos = 0;
				clr_lcd();
				break;
			}
		}
		pos_lcd(1, pos);
		puts_lcd2("  ");
		wait_avr(750);
		updateTime1(end_hr, end_min, end_sec);
		pos_lcd(1, 10);
		puts_lcd2("FINISH");
		wait_avr(1000);
	}
	wait_avr(1000);
	pos_lcd(0,0);
	puts_lcd2("ALARM SET");
	updateTime1();
	wait_avr(1000);
}

void edit_hr_M()
{
	if(start)
	{
		start_hr = (start_hr > 0) ? start_hr - 1 : 23;
	}
	else if(finish)
	{
		end_hr = (end_hr > 0) ? end_hr - 1 : 23;
	}
}

void edit_hr_P()
{
	if(start)
	{
		start_hr = (start_hr < 23) ? start_hr + 1 : 0;
	}
	else if(finish)
	{
		end_hr = (end_hr < 23) ? end_hr + 1 : 0;
	}
}

void edit_min_M()
{
	if(start)
	{
		start_min = (start_min > 0) ? start_min - 1 : 59;
	}
	if(finish)
	{
		end_min = (end_min > 0) ? end_min - 1 : 59;
	}
}

void edit_min_P()
{
	if(start)
	{
		start_min = (start_min < 59) ? start_min + 1 : 0;
	}
	else if(finish)
	{
		end_min = (end_min < 59) ? end_min + 1 : 0;
	}
}

void edit_sec_M()
{
	if(start)
	{
		start_sec = (start_sec > 0) ? start_sec - 1 : 59;
	}
	else if(finish)
	{
		end_sec = (end_sec > 0) ? end_sec - 1 : 59;
	}
}

void edit_sec_P()
{
	if(start)
	{
		start_sec = (start_sec < 59) ? start_sec + 1 : 0;
	}
	else if(finish)
	{
		end_sec = (end_sec < 59) ? end_sec + 1 : 0;
	}
}

void time_state()
{
	pos_lcd(1, 12);
	puts_lcd2("ON");
	current = get_sample();
	int runtime_hr, runtime_min, runtime_sec, ini_hr = 0, ini_min = 0, ini_sec = 0;
	if(end_hr < start_hr)
		runtime_hr = 24 - start_hr + end_hr;
	else
		runtime_hr = end_hr - start_hr;
	if(end_min < start_min)
		runtime_min = 60 - start_min + end_min;
	else
		runtime_min = end_min - start_min;
	if(end_sec < start_sec)
		runtime_sec = 60 - start_sec + end_sec;
	else 
		runtime_sec = end_sec - start_sec;
	while(ini_hr <= runtime_hr && ini_min <= runtime_min && ini_sec < runtime_sec)
	{
		//buttonListen();
		if(get_sample() > current + 20 || get_sample() < current - 20)
			tripped = true;
		if (tripped)
			play_note(1, 10);
		else
		{
			if(ini_sec >= 59)
			{
				ini_sec = 0;
				if(ini_min >= 59)
				{
					ini_min = 0;
					if(ini_hr >= 23)
					{
						ini_hr = 0;
					}
					else
					{
						ini_hr++;
					}
					current = get_sample();
				}
				else
				{
					ini_min++;
				}
			}
			else
			{
				ini_sec++;
			}
			wait_avr(100);
		}
		
	}
}

void buttonListen()
{
	if (GET_BIT(PINC, 4) == 0)
	{
		clr_lcd();
		pos_lcd(0,0);
		puts_lcd2("ALARM OFF");
		running = false;
		tripped = false;
		time_run = false;
		updateTime1(hr, min, sec);
		
	}
	if(GET_BIT(PINC, 5) == 0)
	{
		if(!tripped)
		{
			clr_lcd();
			pos_lcd(0,0);
			puts_lcd2("ALARM ON");
			updateTime1(hr, min, sec);
			running = true;
			init_tripped();
			
		}
		
	}
	if (GET_BIT(PINC, 6) == 0)
	{
		if(!tripped)
		{
			update_edit();
			if(edit_mode == true)
			{
				clr_lcd();
				pos_lcd(0,0);
				puts_lcd2("SET ALARM");
				
				updateTime1(start_hr, start_min, start_sec);
				start = true;
				edit_start();
				wait_avr(100);
			}
		}
	}
	if(GET_BIT(PINC, 7) == 0)
	{
		if(!tripped)
		{
			if(start_hr != end_hr || start_min != end_min || start_sec != end_sec)
			{
				time_run = true;
				clr_lcd();
				pos_lcd(0,0);
				puts_lcd2("TIME ALARM");
				updateTime1(hr, min, sec);
				if(hr == start_hr && min == start_min && sec == start_sec)
					time_state();
			}
			else
			{
				clr_lcd();
				pos_lcd(0,0);
				puts_lcd2("NO ALARM SET");
				updateTime1(hr, min, sec);
			}
		}
		
	}
}

void play_note(unsigned char freq, unsigned char duration)
{
	int i;
	unsigned char tempo = 1;
	for(i = 0; i < duration; i++)
	{
		SET_BIT(PORTA, 1);
		wait_avr(freq * tempo);
		CLR_BIT(PORTA, 1);
		wait_avr(freq * tempo);
		buttonListen();
	}
}

void updateTime1(unsigned short hr, unsigned min, unsigned short sec)
{
	char buf[17];
	pos_lcd(1,0);
	sprintf(buf, "%02d:%02d:%02d", hr, min, sec);
	puts_lcd2(buf);
}

void militaryTime()
{
	//while(1)
	{
		buttonListen();
		if(sec >= 59)
		{
			sec = 0;
			if(min >= 59)
			{
				min = 0;
				if(hr >= 23)
				{
					hr = 0;
				}
				else
				{
					hr++;
				}
				current = get_sample();
			}
			else
			{
				min++;
			}
		}
		else
		{
			sec++;
		}
		updateTime1(hr, min, sec);
		wait_avr(100);
		//clr_lcd();
	}
}

void init_tripped()
{
	current = get_sample();
	tripped = false;
}

void init()
{
		SET_BIT(DDRA,0);
		SET_BIT(DDRA,1);
		init_adc();
		ini_lcd();
		clr_lcd();

		init_tripped();
		current = get_sample();
		pos_lcd(0,0);
		puts_lcd2("ALARM OFF");
}

int main(void)
{
	init();
	
    while(1)
    {
		militaryTime();
		init_keypad();
		buttonListen();
		
		while(running)
		{
			buttonListen();
			if(get_sample() > current + 20 || get_sample() < current - 20)
				tripped = true;
			if (tripped)
				play_note(1, 10);
			else
				militaryTime();
		}
    }
}