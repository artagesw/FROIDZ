public class IO
{
    // ***** I/O REGISTER DEFINITIONS *****************************************
    // NOTE:
    // Definitions marked "MEMORY MAPPED"are extended I/O ports
    // and cannot be used with IN/OUT instructions
    public static final int	UCSR1C	= 0x9d; // MEMORY MAPPED
    public static final int	UDR1	= 0x9c; // MEMORY MAPPED
    public static final int	UCSR1A	= 0x9b; // MEMORY MAPPED
    public static final int	UCSR1B	= 0x9a; // MEMORY MAPPED
    public static final int	UBRR1H	= 0x98; // MEMORY MAPPED
    public static final int	UBRR1L	= 0x99; // MEMORY MAPPED
    public static final int	UCSR0C	= 0x95; // MEMORY MAPPED
    public static final int	UBRR0H	= 0x90; // MEMORY MAPPED
    public static final int	ADCSRB	= 0x8e; // MEMORY MAPPED
    public static final int	TCCR3C	= 0x8c; // MEMORY MAPPED
    public static final int	TCCR3A	= 0x8b; // MEMORY MAPPED
    public static final int	TCCR3B	= 0x8a; // MEMORY MAPPED
    public static final int	TCNT3L	= 0x88; // MEMORY MAPPED
    public static final int	TCNT3H	= 0x89; // MEMORY MAPPED
    public static final int	OCR3AL	= 0x86; // MEMORY MAPPED
    public static final int	OCR3AH	= 0x87; // MEMORY MAPPED
    public static final int	OCR3BL	= 0x84; // MEMORY MAPPED
    public static final int	OCR3BH	= 0x85; // MEMORY MAPPED
    public static final int	OCR3CL	= 0x82; // MEMORY MAPPED
    public static final int	OCR3CH	= 0x83; // MEMORY MAPPED
    public static final int	ICR3L	= 0x80; // MEMORY MAPPED
    public static final int	ICR3H	= 0x81; // MEMORY MAPPED
    public static final int	ETIMSK	= 0x7d; // MEMORY MAPPED
    public static final int	ETIFR	= 0x7c; // MEMORY MAPPED
    public static final int	TCCR1C	= 0x7a; // MEMORY MAPPED
    public static final int	OCR1CL	= 0x78; // MEMORY MAPPED
    public static final int	OCR1CH	= 0x79; // MEMORY MAPPED
    public static final int	TWCR	= 0x74; // MEMORY MAPPED
    public static final int	TWDR	= 0x73; // MEMORY MAPPED
    public static final int	TWAR	= 0x72; // MEMORY MAPPED
    public static final int	TWSR	= 0x71; // MEMORY MAPPED
    public static final int	TWBR	= 0x70; // MEMORY MAPPED
    public static final int	OSCCAL	= 0x6f; // MEMORY MAPPED
    public static final int	XMCRA	= 0x6d; // MEMORY MAPPED
    public static final int	XMCRB	= 0x6c; // MEMORY MAPPED
    public static final int	EICRA	= 0x6a; // MEMORY MAPPED
    public static final int	SPMCSR	= 0x68; // MEMORY MAPPED
    public static final int	PORTG	= 0x65; // MEMORY MAPPED
    public static final int	DDRG	= 0x64; // MEMORY MAPPED
    public static final int	PING	= 0x63; // MEMORY MAPPED
    public static final int	PORTF	= 0x62; // MEMORY MAPPED
    public static final int	DDRF	= 0x61; // MEMORY MAPPED
    public static final int	SREG	= 0x3f;
    public static final int	SPL	= 0x3d;
    public static final int	SPH	= 0x3e;
    public static final int	XDIV	= 0x3c;
    public static final int	RAMPZ	= 0x3b;
    public static final int	EICRB	= 0x3a;
    public static final int	EIMSK	= 0x39;
    public static final int	EIFR	= 0x38;
    public static final int	TIMSK	= 0x37;
    public static final int	TIFR	= 0x36;
    public static final int	MCUCR	= 0x35;
    public static final int	MCUCSR	= 0x34;
    public static final int	TCCR0	= 0x33;
    public static final int	TCNT0	= 0x32;
    public static final int	OCR0	= 0x31;
    public static final int	ASSR	= 0x30;
    public static final int	TCCR1A	= 0x2f;
    public static final int	TCCR1B	= 0x2e;
    public static final int	TCNT1L	= 0x2c;
    public static final int	TCNT1H	= 0x2d;
    public static final int	OCR1AL	= 0x2a;
    public static final int	OCR1AH	= 0x2b;
    public static final int	OCR1BL	= 0x28;
    public static final int	OCR1BH	= 0x29;
    public static final int	ICR1L	= 0x26;
    public static final int	ICR1H	= 0x27;
    public static final int	TCCR2	= 0x25;
    public static final int	TCNT2	= 0x24;
    public static final int	OCR2	= 0x23;
    public static final int	OCDR	= 0x22;
    public static final int	WDTCR	= 0x21;
    public static final int	SFIOR	= 0x20;
    public static final int	EEARL	= 0x1e;
    public static final int	EEARH	= 0x1f;
    public static final int	EEDR	= 0x1d;
    public static final int	EECR	= 0x1c;
    public static final int	PORTA	= 0x1b;
    public static final int	DDRA	= 0x1a;
    public static final int	PINA	= 0x19;
    public static final int	PORTB	= 0x18;
    public static final int	DDRB	= 0x17;
    public static final int	PINB	= 0x16;
    public static final int	PORTC	= 0x15;
    public static final int	DDRC	= 0x14;
    public static final int	PINC	= 0x13;
    public static final int	PORTD	= 0x12;
    public static final int	DDRD	= 0x11;
    public static final int	PIND	= 0x10;
    public static final int	SPDR	= 0x0f;
    public static final int	SPSR	= 0x0e;
    public static final int	SPCR	= 0x0d;
    public static final int	UDR0	= 0x0c;
    public static final int	UCSR0A	= 0x0b;
    public static final int	UCSR0B	= 0x0a;
    public static final int	UBRR0L	= 0x09;
    public static final int	ACSR	= 0x08;
    public static final int	ADMUX	= 0x07;
    public static final int	ADCSRA	= 0x06;
    public static final int	ADCH	= 0x05;
    public static final int	ADCL	= 0x04;
    public static final int	PORTE	= 0x03;
    public static final int	DDRE	= 0x02;
    public static final int	PINE	= 0x01;
    public static final int	PINF	= 0x00;


    ; // ***** BIT DEFINITIONS **************************************************

    ; // ***** ANALOG_COMPARATOR ************
    ; // SFIOR - Special Function IO Register
    public static final int	ACME	= 3	; // Analog Comparator Multiplexer Enable

    ; // ACSR - Analog Comparator Control And Status Register
    public static final int	ACIS0	= 0	; // Analog Comparator Interrupt Mode Select bit 0
    public static final int	ACIS1	= 1	; // Analog Comparator Interrupt Mode Select bit 1
    public static final int	ACIC	= 2	; // Analog Comparator Input Capture Enable
    public static final int	ACIE	= 3	; // Analog Comparator Interrupt Enable
    public static final int	ACI	= 4	; // Analog Comparator Interrupt Flag
    public static final int	ACO	= 5	; // Analog Compare Output
    public static final int	ACBG	= 6	; // Analog Comparator Bandgap Select
    public static final int	ACD	= 7	; // Analog Comparator Disable


    ; // ***** AD_CONVERTER *****************
    ; // ADMUX - The ADC multiplexer Selection Register
    public static final int	MUX0	= 0	; // Analog Channel and Gain Selection Bits
    public static final int	MUX1	= 1	; // Analog Channel and Gain Selection Bits
    public static final int	MUX2	= 2	; // Analog Channel and Gain Selection Bits
    public static final int	MUX3	= 3	; // Analog Channel and Gain Selection Bits
    public static final int	MUX4	= 4	; // Analog Channel and Gain Selection Bits
    public static final int	ADLAR	= 5	; // Left Adjust Result
    public static final int	REFS0	= 6	; // Reference Selection Bit 0
    public static final int	REFS1	= 7	; // Reference Selection Bit 1

    ; // ADCSRA - The ADC Control and Status register A
    public static final int	ADCSR	= ADCSRA	; // For compatibility
    public static final int	ADPS0	= 0	; // ADC  Prescaler Select Bits
    public static final int	ADPS1	= 1	; // ADC  Prescaler Select Bits
    public static final int	ADPS2	= 2	; // ADC  Prescaler Select Bits
    public static final int	ADIE	= 3	; // ADC Interrupt Enable
    public static final int	ADIF	= 4	; // ADC Interrupt Flag
    public static final int	ADATE	= 5	; // ADC  Auto Trigger Enable
    public static final int	ADFR	= ADATE	; // For compatibility
    public static final int	ADSC	= 6	; // ADC Start Conversion
    public static final int	ADEN	= 7	; // ADC Enable

    ; // ADCH - ADC Data Register High Byte
    public static final int	ADCH0	= 0	; // ADC Data Register High Byte Bit 0
    public static final int	ADCH1	= 1	; // ADC Data Register High Byte Bit 1
    public static final int	ADCH2	= 2	; // ADC Data Register High Byte Bit 2
    public static final int	ADCH3	= 3	; // ADC Data Register High Byte Bit 3
    public static final int	ADCH4	= 4	; // ADC Data Register High Byte Bit 4
    public static final int	ADCH5	= 5	; // ADC Data Register High Byte Bit 5
    public static final int	ADCH6	= 6	; // ADC Data Register High Byte Bit 6
    public static final int	ADCH7	= 7	; // ADC Data Register High Byte Bit 7

    ; // ADCL - ADC Data Register Low Byte
    public static final int	ADCL0	= 0	; // ADC Data Register Low Byte Bit 0
    public static final int	ADCL1	= 1	; // ADC Data Register Low Byte Bit 1
    public static final int	ADCL2	= 2	; // ADC Data Register Low Byte Bit 2
    public static final int	ADCL3	= 3	; // ADC Data Register Low Byte Bit 3
    public static final int	ADCL4	= 4	; // ADC Data Register Low Byte Bit 4
    public static final int	ADCL5	= 5	; // ADC Data Register Low Byte Bit 5
    public static final int	ADCL6	= 6	; // ADC Data Register Low Byte Bit 6
    public static final int	ADCL7	= 7	; // ADC Data Register Low Byte Bit 7

    ; // ADCSRB - The ADC Control and Status register B
    public static final int	ADTS0	= 0	; // ADC Auto Trigger Source bit 0
    public static final int	ADTS1	= 1	; // ADC Auto Trigger Source bit 1
    public static final int	ADTS2	= 2	; // ADC Auto Trigger Source bit 2


    ; // ***** SPI **************************
    ; // SPDR - SPI Data Register
    public static final int	SPDR0	= 0	; // SPI Data Register bit 0
    public static final int	SPDR1	= 1	; // SPI Data Register bit 1
    public static final int	SPDR2	= 2	; // SPI Data Register bit 2
    public static final int	SPDR3	= 3	; // SPI Data Register bit 3
    public static final int	SPDR4	= 4	; // SPI Data Register bit 4
    public static final int	SPDR5	= 5	; // SPI Data Register bit 5
    public static final int	SPDR6	= 6	; // SPI Data Register bit 6
    public static final int	SPDR7	= 7	; // SPI Data Register bit 7

    ; // SPSR - SPI Status Register
    public static final int	SPI2X	= 0	; // Double SPI Speed Bit
    public static final int	WCOL	= 6	; // Write Collision Flag
    public static final int	SPIF	= 7	; // SPI Interrupt Flag

    ; // SPCR - SPI Control Register
    public static final int	SPR0	= 0	; // SPI Clock Rate Select 0
    public static final int	SPR1	= 1	; // SPI Clock Rate Select 1
    public static final int	CPHA	= 2	; // Clock Phase
    public static final int	CPOL	= 3	; // Clock polarity
    public static final int	MSTR	= 4	; // Master/Slave Select
    public static final int	DORD	= 5	; // Data Order
    public static final int	SPE	= 6	; // SPI Enable
    public static final int	SPIE	= 7	; // SPI Interrupt Enable


    ; // ***** TWI **************************
    ; // TWBR - TWI Bit Rate register
    public static final int	I2BR	= TWBR	; // For compatibility
    public static final int	TWBR0	= 0	; // 
    public static final int	TWBR1	= 1	; // 
    public static final int	TWBR2	= 2	; // 
    public static final int	TWBR3	= 3	; // 
    public static final int	TWBR4	= 4	; // 
    public static final int	TWBR5	= 5	; // 
    public static final int	TWBR6	= 6	; // 
    public static final int	TWBR7	= 7	; // 

    ; // TWCR - TWI Control Register
    public static final int	I2CR	= TWCR	; // For compatibility
    public static final int	TWIE	= 0	; // TWI Interrupt Enable
    public static final int	I2IE	= TWIE	; // For compatibility
    public static final int	TWEN	= 2	; // TWI Enable Bit
    public static final int	I2EN	= TWEN	; // For compatibility
    public static final int	ENI2C	= TWEN	; // For compatibility
    public static final int	TWWC	= 3	; // TWI Write Collition Flag
    public static final int	I2WC	= TWWC	; // For compatibility
    public static final int	TWSTO	= 4	; // TWI Stop Condition Bit
    public static final int	I2STO	= TWSTO	; // For compatibility
    public static final int	TWSTA	= 5	; // TWI Start Condition Bit
    public static final int	I2STA	= TWSTA	; // For compatibility
    public static final int	TWEA	= 6	; // TWI Enable Acknowledge Bit
    public static final int	I2EA	= TWEA	; // For compatibility
    public static final int	TWINT	= 7	; // TWI Interrupt Flag
    public static final int	I2INT	= TWINT	; // For compatibility

    ; // TWSR - TWI Status Register
    public static final int	I2SR	= TWSR	; // For compatibility
    public static final int	TWPS0	= 0	; // TWI Prescaler
    public static final int	TWS0	= TWPS0	; // For compatibility
    public static final int	I2GCE	= TWPS0	; // For compatibility
    public static final int	TWPS1	= 1	; // TWI Prescaler
    public static final int	TWS1	= TWPS1	; // For compatibility
    public static final int	TWS3	= 3	; // TWI Status
    public static final int	I2S3	= TWS3	; // For compatibility
    public static final int	TWS4	= 4	; // TWI Status
    public static final int	I2S4	= TWS4	; // For compatibility
    public static final int	TWS5	= 5	; // TWI Status
    public static final int	I2S5	= TWS5	; // For compatibility
    public static final int	TWS6	= 6	; // TWI Status
    public static final int	I2S6	= TWS6	; // For compatibility
    public static final int	TWS7	= 7	; // TWI Status
    public static final int	I2S7	= TWS7	; // For compatibility

    ; // TWDR - TWI Data register
    public static final int	I2DR	= TWDR	; // For compatibility
    public static final int	TWD0	= 0	; // TWI Data Register Bit 0
    public static final int	TWD1	= 1	; // TWI Data Register Bit 1
    public static final int	TWD2	= 2	; // TWI Data Register Bit 2
    public static final int	TWD3	= 3	; // TWI Data Register Bit 3
    public static final int	TWD4	= 4	; // TWI Data Register Bit 4
    public static final int	TWD5	= 5	; // TWI Data Register Bit 5
    public static final int	TWD6	= 6	; // TWI Data Register Bit 6
    public static final int	TWD7	= 7	; // TWI Data Register Bit 7

    ; // TWAR - TWI (Slave) Address register
    public static final int	I2AR	= TWAR	; // For compatibility
    public static final int	TWGCE	= 0	; // TWI General Call Recognition Enable Bit
    public static final int	TWA0	= 1	; // TWI (Slave) Address register Bit 0
    public static final int	TWA1	= 2	; // TWI (Slave) Address register Bit 1
    public static final int	TWA2	= 3	; // TWI (Slave) Address register Bit 2
    public static final int	TWA3	= 4	; // TWI (Slave) Address register Bit 3
    public static final int	TWA4	= 5	; // TWI (Slave) Address register Bit 4
    public static final int	TWA5	= 6	; // TWI (Slave) Address register Bit 5
    public static final int	TWA6	= 7	; // TWI (Slave) Address register Bit 6


    ; // ***** USART0 ***********************
    ; // UDR0 - USART I/O Data Register
    public static final int	UDR00	= 0	; // USART I/O Data Register bit 0
    public static final int	UDR01	= 1	; // USART I/O Data Register bit 1
    public static final int	UDR02	= 2	; // USART I/O Data Register bit 2
    public static final int	UDR03	= 3	; // USART I/O Data Register bit 3
    public static final int	UDR04	= 4	; // USART I/O Data Register bit 4
    public static final int	UDR05	= 5	; // USART I/O Data Register bit 5
    public static final int	UDR06	= 6	; // USART I/O Data Register bit 6
    public static final int	UDR07	= 7	; // USART I/O Data Register bit 7

    ; // UCSR0A - USART Control and Status Register A
    public static final int	MPCM0	= 0	; // Multi-processor Communication Mode
    public static final int	U2X0	= 1	; // Double the USART transmission speed
    public static final int	UPE0	= 2	; // Parity Error
    public static final int	DOR0	= 3	; // Data overRun
    public static final int	FE0	= 4	; // Framing Error
    public static final int	UDRE0	= 5	; // USART Data Register Empty
    public static final int	TXC0	= 6	; // USART Transmitt Complete
    public static final int	RXC0	= 7	; // USART Receive Complete

    ; // UCSR0B - USART Control and Status Register B
    public static final int	TXB80	= 0	; // Transmit Data Bit 8
    public static final int	RXB80	= 1	; // Receive Data Bit 8
    public static final int	UCSZ02	= 2	; // Character Size
    public static final int	UCSZ2	= UCSZ02	; // For compatibility
    public static final int	TXEN0	= 3	; // Transmitter Enable
    public static final int	RXEN0	= 4	; // Receiver Enable
    public static final int	UDRIE0	= 5	; // USART Data register Empty Interrupt Enable
    public static final int	TXCIE0	= 6	; // TX Complete Interrupt Enable
    public static final int	RXCIE0	= 7	; // RX Complete Interrupt Enable

    ; // UCSR0C - USART Control and Status Register C
    public static final int	UCPOL0	= 0	; // Clock Polarity
    public static final int	UCSZ00	= 1	; // Character Size
    public static final int	UCSZ01	= 2	; // Character Size
    public static final int	USBS0	= 3	; // Stop Bit Select
    public static final int	UPM00	= 4	; // Parity Mode Bit 0
    public static final int	UPM01	= 5	; // Parity Mode Bit 1
    public static final int	UMSEL0	= 6	; // USART Mode Select

    ; // UBRR0H - USART Baud Rate Register Hight Byte
    public static final int	UBRR8	= 0	; // USART Baud Rate Register bit 8
    public static final int	UBRR9	= 1	; // USART Baud Rate Register bit 9
    public static final int	UBRR10	= 2	; // USART Baud Rate Register bit 10
    public static final int	UBRR11	= 3	; // USART Baud Rate Register bit 11

    ; // UBRR0L - USART Baud Rate Register Low Byte
    public static final int	UBRR0	= 0	; // USART Baud Rate Register bit 0
    public static final int	UBRR1	= 1	; // USART Baud Rate Register bit 1
    public static final int	UBRR2	= 2	; // USART Baud Rate Register bit 2
    public static final int	UBRR3	= 3	; // USART Baud Rate Register bit 3
    public static final int	UBRR4	= 4	; // USART Baud Rate Register bit 4
    public static final int	UBRR5	= 5	; // USART Baud Rate Register bit 5
    public static final int	UBRR6	= 6	; // USART Baud Rate Register bit 6
    public static final int	UBRR7	= 7	; // USART Baud Rate Register bit 7


    ; // ***** USART1 ***********************
    ; // UDR1 - USART I/O Data Register
    public static final int	UDR10	= 0	; // USART I/O Data Register bit 0
    public static final int	UDR11	= 1	; // USART I/O Data Register bit 1
    public static final int	UDR12	= 2	; // USART I/O Data Register bit 2
    public static final int	UDR13	= 3	; // USART I/O Data Register bit 3
    public static final int	UDR14	= 4	; // USART I/O Data Register bit 4
    public static final int	UDR15	= 5	; // USART I/O Data Register bit 5
    public static final int	UDR16	= 6	; // USART I/O Data Register bit 6
    public static final int	UDR17	= 7	; // USART I/O Data Register bit 7

    ; // UCSR1A - USART Control and Status Register A
    public static final int	MPCM1	= 0	; // Multi-processor Communication Mode
    public static final int	U2X1	= 1	; // Double the USART transmission speed
    public static final int	UPE1	= 2	; // Parity Error
    public static final int	DOR1	= 3	; // Data overRun
    public static final int	FE1	= 4	; // Framing Error
    public static final int	UDRE1	= 5	; // USART Data Register Empty
    public static final int	TXC1	= 6	; // USART Transmitt Complete
    public static final int	RXC1	= 7	; // USART Receive Complete

    ; // UCSR1B - USART Control and Status Register B
    public static final int	TXB81	= 0	; // Transmit Data Bit 8
    public static final int	RXB81	= 1	; // Receive Data Bit 8
    public static final int	UCSZ12	= 2	; // Character Size
    public static final int	TXEN1	= 3	; // Transmitter Enable
    public static final int	RXEN1	= 4	; // Receiver Enable
    public static final int	UDRIE1	= 5	; // USART Data register Empty Interrupt Enable
    public static final int	TXCIE1	= 6	; // TX Complete Interrupt Enable
    public static final int	RXCIE1	= 7	; // RX Complete Interrupt Enable

    ; // UCSR1C - USART Control and Status Register C
    public static final int	UCPOL1	= 0	; // Clock Polarity
    public static final int	UCSZ10	= 1	; // Character Size
    public static final int	UCSZ11	= 2	; // Character Size
    public static final int	USBS1	= 3	; // Stop Bit Select
    public static final int	UPM10	= 4	; // Parity Mode Bit 0
    public static final int	UPM11	= 5	; // Parity Mode Bit 1
    public static final int	UMSEL1	= 6	; // USART Mode Select

    ; // ***** CPU **************************
    ; // SREG - Status Register
    public static final int	SREG_C	= 0	; // Carry Flag
    public static final int	SREG_Z	= 1	; // Zero Flag
    public static final int	SREG_N	= 2	; // Negative Flag
    public static final int	SREG_V	= 3	; // Two's Complement Overflow Flag
    public static final int	SREG_S	= 4	; // Sign Bit
    public static final int	SREG_H	= 5	; // Half Carry Flag
    public static final int	SREG_T	= 6	; // Bit Copy Storage
    public static final int	SREG_I	= 7	; // Global Interrupt Enable

    ; // MCUCR - MCU Control Register
    public static final int	IVCE	= 0	; // Interrupt Vector Change Enable
    public static final int	IVSEL	= 1	; // Interrupt Vector Select
    public static final int	SM2	= 2	; // Sleep Mode Select
    public static final int	SM0	= 3	; // Sleep Mode Select
    public static final int	SM1	= 4	; // Sleep Mode Select
    public static final int	SE	= 5	; // Sleep Enable
    public static final int	SRW10	= 6	; // External SRAM Wait State Select
    public static final int	SRE	= 7	; // External SRAM Enable

    ; // XMCRA - External Memory Control Register A
    public static final int	SRW11	= 1	; // Wait state select bit upper page
    public static final int	SRW00	= 2	; // Wait state select bit lower page
    public static final int	SRW01	= 3	; // Wait state select bit lower page
    public static final int	SRL0	= 4	; // Wait state page limit
    public static final int	SRL1	= 5	; // Wait state page limit
    public static final int	SRL2	= 6	; // Wait state page limit

    ; // XMCRB - External Memory Control Register B
    public static final int	XMM0	= 0	; // External Memory High Mask
    public static final int	XMM1	= 1	; // External Memory High Mask
    public static final int	XMM2	= 2	; // External Memory High Mask
    public static final int	XMBK	= 7	; // External Memory Bus Keeper Enable

    ; // OSCCAL - Oscillator Calibration Value
    public static final int	CAL0	= 0	; // Oscillator Calibration Value
    public static final int	CAL1	= 1	; // Oscillator Calibration Value
    public static final int	CAL2	= 2	; // Oscillator Calibration Value
    public static final int	CAL3	= 3	; // Oscillator Calibration Value
    public static final int	CAL4	= 4	; // Oscillator Calibration Value
    public static final int	CAL5	= 5	; // Oscillator Calibration Value
    public static final int	CAL6	= 6	; // Oscillator Calibration Value
    public static final int	CAL7	= 7	; // Oscillator Calibration Value

    ; // XDIV - XTAL Divide Control Register
    public static final int	XDIV0	= 0	; // XTAl Divide Select Bit 0
    public static final int	XDIV1	= 1	; // XTAl Divide Select Bit 1
    public static final int	XDIV2	= 2	; // XTAl Divide Select Bit 2
    public static final int	XDIV3	= 3	; // XTAl Divide Select Bit 3
    public static final int	XDIV4	= 4	; // XTAl Divide Select Bit 4
    public static final int	XDIV5	= 5	; // XTAl Divide Select Bit 5
    public static final int	XDIV6	= 6	; // XTAl Divide Select Bit 6
    public static final int	XDIVEN	= 7	; // XTAL Divide Enable

    ; // MCUCSR - MCU Control And Status Register
    public static final int	PORF	= 0	; // Power-on reset flag
    public static final int	EXTRF	= 1	; // External Reset Flag
    public static final int	BORF	= 2	; // Brown-out Reset Flag
    public static final int	WDRF	= 3	; // Watchdog Reset Flag
    public static final int	JTRF	= 4	; // JTAG Reset Flag
    public static final int	JTD	= 7	; // JTAG Interface Disable


    ; // ***** BOOT_LOAD ********************
    ; // SPMCSR - Store Program Memory Control Register
    public static final int	SPMCR	= SPMCSR	; // For compatibility
    public static final int	SPMEN	= 0	; // Store Program Memory Enable
    public static final int	PGERS	= 1	; // Page Erase
    public static final int	PGWRT	= 2	; // Page Write
    public static final int	BLBSET	= 3	; // Boot Lock Bit Set
    public static final int	RWWSRE	= 4	; // Read While Write section read enable
    public static final int	ASRE	= RWWSRE	; // For compatibility
    public static final int	RWWSB	= 6	; // Read While Write Section Busy
    public static final int	ASB	= RWWSB	; // For compatibility
    public static final int	SPMIE	= 7	; // SPM Interrupt Enable


    ; // ***** JTAG *************************
    ; // OCDR - On-Chip Debug Related Register in I/O Memory
    public static final int	OCDR0	= 0	; // On-Chip Debug Register Bit 0
    public static final int	OCDR1	= 1	; // On-Chip Debug Register Bit 1
    public static final int	OCDR2	= 2	; // On-Chip Debug Register Bit 2
    public static final int	OCDR3	= 3	; // On-Chip Debug Register Bit 3
    public static final int	OCDR4	= 4	; // On-Chip Debug Register Bit 4
    public static final int	OCDR5	= 5	; // On-Chip Debug Register Bit 5
    public static final int	OCDR6	= 6	; // On-Chip Debug Register Bit 6
    public static final int	OCDR7	= 7	; // On-Chip Debug Register Bit 7
    public static final int	IDRD	= OCDR7	; // For compatibility

    ; // ***** MISC *************************
    ; // SFIOR - Special Function IO Register
    public static final int	PSR321	= 0	; // Prescaler Reset Timer/Counter3, Timer/Counter2, and Timer/Counter1
    public static final int	PSR1	= PSR321	; // For compatibility
    public static final int	PSR2	= PSR321	; // For compatibility
    public static final int	PSR3	= PSR321	; // For compatibility
    public static final int	PSR0	= 1	; // Prescaler Reset Timer/Counter0
    public static final int	PUD	= 2	; // Pull Up Disable
    ; //public static final int	ACME	= 3	; // Analog Comparator Multiplexer Enable
    public static final int	TSM	= 7	; // Timer/Counter Synchronization Mode


    ; // ***** EXTERNAL_INTERRUPT ***********
    ; // EICRA - External Interrupt Control Register A
    public static final int	ISC00	= 0	; // External Interrupt Sense Control Bit
    public static final int	ISC01	= 1	; // External Interrupt Sense Control Bit
    public static final int	ISC10	= 2	; // External Interrupt Sense Control Bit
    public static final int	ISC11	= 3	; // External Interrupt Sense Control Bit
    public static final int	ISC20	= 4	; // External Interrupt Sense Control Bit
    public static final int	ISC21	= 5	; // External Interrupt Sense Control Bit
    public static final int	ISC30	= 6	; // External Interrupt Sense Control Bit
    public static final int	ISC31	= 7	; // External Interrupt Sense Control Bit

    ; // EICRB - External Interrupt Control Register B
    public static final int	ISC40	= 0	; // External Interrupt 7-4 Sense Control Bit
    public static final int	ISC41	= 1	; // External Interrupt 7-4 Sense Control Bit
    public static final int	ISC50	= 2	; // External Interrupt 7-4 Sense Control Bit
    public static final int	ISC51	= 3	; // External Interrupt 7-4 Sense Control Bit
    public static final int	ISC60	= 4	; // External Interrupt 7-4 Sense Control Bit
    public static final int	ISC61	= 5	; // External Interrupt 7-4 Sense Control Bit
    public static final int	ISC70	= 6	; // External Interrupt 7-4 Sense Control Bit
    public static final int	ISC71	= 7	; // External Interrupt 7-4 Sense Control Bit

    ; // EIMSK - External Interrupt Mask Register
    public static final int	GICR	= EIMSK	; // For compatibility
    public static final int	GIMSK	= EIMSK	; // For compatibility
    public static final int	INT0	= 0	; // External Interrupt Request 0 Enable
    public static final int	INT1	= 1	; // External Interrupt Request 1 Enable
    public static final int	INT2	= 2	; // External Interrupt Request 2 Enable
    public static final int	INT3	= 3	; // External Interrupt Request 3 Enable
    public static final int	INT4	= 4	; // External Interrupt Request 4 Enable
    public static final int	INT5	= 5	; // External Interrupt Request 5 Enable
    public static final int	INT6	= 6	; // External Interrupt Request 6 Enable
    public static final int	INT7	= 7	; // External Interrupt Request 7 Enable

    ; // EIFR - External Interrupt Flag Register
    public static final int	GIFR	= EIFR	; // For compatibility
    public static final int	INTF0	= 0	; // External Interrupt Flag 0
    public static final int	INTF1	= 1	; // External Interrupt Flag 1
    public static final int	INTF2	= 2	; // External Interrupt Flag 2
    public static final int	INTF3	= 3	; // External Interrupt Flag 3
    public static final int	INTF4	= 4	; // External Interrupt Flag 4
    public static final int	INTF5	= 5	; // External Interrupt Flag 5
    public static final int	INTF6	= 6	; // External Interrupt Flag 6
    public static final int	INTF7	= 7	; // External Interrupt Flag 7


    ; // ***** EEPROM ***********************
    ; // EEDR - EEPROM Data Register
    public static final int	EEDR0	= 0	; // EEPROM Data Register bit 0
    public static final int	EEDR1	= 1	; // EEPROM Data Register bit 1
    public static final int	EEDR2	= 2	; // EEPROM Data Register bit 2
    public static final int	EEDR3	= 3	; // EEPROM Data Register bit 3
    public static final int	EEDR4	= 4	; // EEPROM Data Register bit 4
    public static final int	EEDR5	= 5	; // EEPROM Data Register bit 5
    public static final int	EEDR6	= 6	; // EEPROM Data Register bit 6
    public static final int	EEDR7	= 7	; // EEPROM Data Register bit 7

    ; // EECR - EEPROM Control Register
    public static final int	EERE	= 0	; // EEPROM Read Enable
    public static final int	EEWE	= 1	; // EEPROM Write Enable
    public static final int	EEMWE	= 2	; // EEPROM Master Write Enable
    public static final int	EERIE	= 3	; // EEPROM Ready Interrupt Enable


    ; // ***** PORTA ************************
    ; // PORTA - Port A Data Register
    public static final int	PORTA0	= 0	; // Port A Data Register bit 0
    public static final int	PA0	= 0	; // For compatibility
    public static final int	PORTA1	= 1	; // Port A Data Register bit 1
    public static final int	PA1	= 1	; // For compatibility
    public static final int	PORTA2	= 2	; // Port A Data Register bit 2
    public static final int	PA2	= 2	; // For compatibility
    public static final int	PORTA3	= 3	; // Port A Data Register bit 3
    public static final int	PA3	= 3	; // For compatibility
    public static final int	PORTA4	= 4	; // Port A Data Register bit 4
    public static final int	PA4	= 4	; // For compatibility
    public static final int	PORTA5	= 5	; // Port A Data Register bit 5
    public static final int	PA5	= 5	; // For compatibility
    public static final int	PORTA6	= 6	; // Port A Data Register bit 6
    public static final int	PA6	= 6	; // For compatibility
    public static final int	PORTA7	= 7	; // Port A Data Register bit 7
    public static final int	PA7	= 7	; // For compatibility

    ; // DDRA - Port A Data Direction Register
    public static final int	DDA0	= 0	; // Data Direction Register, Port A, bit 0
    public static final int	DDA1	= 1	; // Data Direction Register, Port A, bit 1
    public static final int	DDA2	= 2	; // Data Direction Register, Port A, bit 2
    public static final int	DDA3	= 3	; // Data Direction Register, Port A, bit 3
    public static final int	DDA4	= 4	; // Data Direction Register, Port A, bit 4
    public static final int	DDA5	= 5	; // Data Direction Register, Port A, bit 5
    public static final int	DDA6	= 6	; // Data Direction Register, Port A, bit 6
    public static final int	DDA7	= 7	; // Data Direction Register, Port A, bit 7

    ; // PINA - Port A Input Pins
    public static final int	PINA0	= 0	; // Input Pins, Port A bit 0
    public static final int	PINA1	= 1	; // Input Pins, Port A bit 1
    public static final int	PINA2	= 2	; // Input Pins, Port A bit 2
    public static final int	PINA3	= 3	; // Input Pins, Port A bit 3
    public static final int	PINA4	= 4	; // Input Pins, Port A bit 4
    public static final int	PINA5	= 5	; // Input Pins, Port A bit 5
    public static final int	PINA6	= 6	; // Input Pins, Port A bit 6
    public static final int	PINA7	= 7	; // Input Pins, Port A bit 7


    ; // ***** PORTB ************************
    ; // PORTB - Port B Data Register
    public static final int	PORTB0	= 0	; // Port B Data Register bit 0
    public static final int	PB0	= 0	; // For compatibility
    public static final int	PORTB1	= 1	; // Port B Data Register bit 1
    public static final int	PB1	= 1	; // For compatibility
    public static final int	PORTB2	= 2	; // Port B Data Register bit 2
    public static final int	PB2	= 2	; // For compatibility
    public static final int	PORTB3	= 3	; // Port B Data Register bit 3
    public static final int	PB3	= 3	; // For compatibility
    public static final int	PORTB4	= 4	; // Port B Data Register bit 4
    public static final int	PB4	= 4	; // For compatibility
    public static final int	PORTB5	= 5	; // Port B Data Register bit 5
    public static final int	PB5	= 5	; // For compatibility
    public static final int	PORTB6	= 6	; // Port B Data Register bit 6
    public static final int	PB6	= 6	; // For compatibility
    public static final int	PORTB7	= 7	; // Port B Data Register bit 7
    public static final int	PB7	= 7	; // For compatibility

    ; // DDRB - Port B Data Direction Register
    public static final int	DDB0	= 0	; // Port B Data Direction Register bit 0
    public static final int	DDB1	= 1	; // Port B Data Direction Register bit 1
    public static final int	DDB2	= 2	; // Port B Data Direction Register bit 2
    public static final int	DDB3	= 3	; // Port B Data Direction Register bit 3
    public static final int	DDB4	= 4	; // Port B Data Direction Register bit 4
    public static final int	DDB5	= 5	; // Port B Data Direction Register bit 5
    public static final int	DDB6	= 6	; // Port B Data Direction Register bit 6
    public static final int	DDB7	= 7	; // Port B Data Direction Register bit 7

    ; // PINB - Port B Input Pins
    public static final int	PINB0	= 0	; // Port B Input Pins bit 0
    public static final int	PINB1	= 1	; // Port B Input Pins bit 1
    public static final int	PINB2	= 2	; // Port B Input Pins bit 2
    public static final int	PINB3	= 3	; // Port B Input Pins bit 3
    public static final int	PINB4	= 4	; // Port B Input Pins bit 4
    public static final int	PINB5	= 5	; // Port B Input Pins bit 5
    public static final int	PINB6	= 6	; // Port B Input Pins bit 6
    public static final int	PINB7	= 7	; // Port B Input Pins bit 7


    ; // ***** PORTC ************************
    ; // PORTC - Port C Data Register
    public static final int	PORTC0	= 0	; // Port C Data Register bit 0
    public static final int	PC0	= 0	; // For compatibility
    public static final int	PORTC1	= 1	; // Port C Data Register bit 1
    public static final int	PC1	= 1	; // For compatibility
    public static final int	PORTC2	= 2	; // Port C Data Register bit 2
    public static final int	PC2	= 2	; // For compatibility
    public static final int	PORTC3	= 3	; // Port C Data Register bit 3
    public static final int	PC3	= 3	; // For compatibility
    public static final int	PORTC4	= 4	; // Port C Data Register bit 4
    public static final int	PC4	= 4	; // For compatibility
    public static final int	PORTC5	= 5	; // Port C Data Register bit 5
    public static final int	PC5	= 5	; // For compatibility
    public static final int	PORTC6	= 6	; // Port C Data Register bit 6
    public static final int	PC6	= 6	; // For compatibility
    public static final int	PORTC7	= 7	; // Port C Data Register bit 7
    public static final int	PC7	= 7	; // For compatibility

    ; // DDRC - Port C Data Direction Register
    public static final int	DDC0	= 0	; // Port C Data Direction Register bit 0
    public static final int	DDC1	= 1	; // Port C Data Direction Register bit 1
    public static final int	DDC2	= 2	; // Port C Data Direction Register bit 2
    public static final int	DDC3	= 3	; // Port C Data Direction Register bit 3
    public static final int	DDC4	= 4	; // Port C Data Direction Register bit 4
    public static final int	DDC5	= 5	; // Port C Data Direction Register bit 5
    public static final int	DDC6	= 6	; // Port C Data Direction Register bit 6
    public static final int	DDC7	= 7	; // Port C Data Direction Register bit 7

    ; // PINC - Port C Input Pins
    public static final int	PINC0	= 0	; // Port C Input Pins bit 0
    public static final int	PINC1	= 1	; // Port C Input Pins bit 1
    public static final int	PINC2	= 2	; // Port C Input Pins bit 2
    public static final int	PINC3	= 3	; // Port C Input Pins bit 3
    public static final int	PINC4	= 4	; // Port C Input Pins bit 4
    public static final int	PINC5	= 5	; // Port C Input Pins bit 5
    public static final int	PINC6	= 6	; // Port C Input Pins bit 6
    public static final int	PINC7	= 7	; // Port C Input Pins bit 7


    ; // ***** PORTD ************************
    ; // PORTD - Port D Data Register
    public static final int	PORTD0	= 0	; // Port D Data Register bit 0
    public static final int	PD0	= 0	; // For compatibility
    public static final int	PORTD1	= 1	; // Port D Data Register bit 1
    public static final int	PD1	= 1	; // For compatibility
    public static final int	PORTD2	= 2	; // Port D Data Register bit 2
    public static final int	PD2	= 2	; // For compatibility
    public static final int	PORTD3	= 3	; // Port D Data Register bit 3
    public static final int	PD3	= 3	; // For compatibility
    public static final int	PORTD4	= 4	; // Port D Data Register bit 4
    public static final int	PD4	= 4	; // For compatibility
    public static final int	PORTD5	= 5	; // Port D Data Register bit 5
    public static final int	PD5	= 5	; // For compatibility
    public static final int	PORTD6	= 6	; // Port D Data Register bit 6
    public static final int	PD6	= 6	; // For compatibility
    public static final int	PORTD7	= 7	; // Port D Data Register bit 7
    public static final int	PD7	= 7	; // For compatibility

    ; // DDRD - Port D Data Direction Register
    public static final int	DDD0	= 0	; // Port D Data Direction Register bit 0
    public static final int	DDD1	= 1	; // Port D Data Direction Register bit 1
    public static final int	DDD2	= 2	; // Port D Data Direction Register bit 2
    public static final int	DDD3	= 3	; // Port D Data Direction Register bit 3
    public static final int	DDD4	= 4	; // Port D Data Direction Register bit 4
    public static final int	DDD5	= 5	; // Port D Data Direction Register bit 5
    public static final int	DDD6	= 6	; // Port D Data Direction Register bit 6
    public static final int	DDD7	= 7	; // Port D Data Direction Register bit 7

    ; // PIND - Port D Input Pins
    public static final int	PIND0	= 0	; // Port D Input Pins bit 0
    public static final int	PIND1	= 1	; // Port D Input Pins bit 1
    public static final int	PIND2	= 2	; // Port D Input Pins bit 2
    public static final int	PIND3	= 3	; // Port D Input Pins bit 3
    public static final int	PIND4	= 4	; // Port D Input Pins bit 4
    public static final int	PIND5	= 5	; // Port D Input Pins bit 5
    public static final int	PIND6	= 6	; // Port D Input Pins bit 6
    public static final int	PIND7	= 7	; // Port D Input Pins bit 7


    ; // ***** PORTE ************************
    ; // PORTE - Data Register, Port E
    public static final int	PORTE0	= 0	; // 
    public static final int	PE0	= 0	; // For compatibility
    public static final int	PORTE1	= 1	; // 
    public static final int	PE1	= 1	; // For compatibility
    public static final int	PORTE2	= 2	; // 
    public static final int	PE2	= 2	; // For compatibility
    public static final int	PORTE3	= 3	; // 
    public static final int	PE3	= 3	; // For compatibility
    public static final int	PORTE4	= 4	; // 
    public static final int	PE4	= 4	; // For compatibility
    public static final int	PORTE5	= 5	; // 
    public static final int	PE5	= 5	; // For compatibility
    public static final int	PORTE6	= 6	; // 
    public static final int	PE6	= 6	; // For compatibility
    public static final int	PORTE7	= 7	; // 
    public static final int	PE7	= 7	; // For compatibility

    ; // DDRE - Data Direction Register, Port E
    public static final int	DDE0	= 0	; // 
    public static final int	DDE1	= 1	; // 
    public static final int	DDE2	= 2	; // 
    public static final int	DDE3	= 3	; // 
    public static final int	DDE4	= 4	; // 
    public static final int	DDE5	= 5	; // 
    public static final int	DDE6	= 6	; // 
    public static final int	DDE7	= 7	; // 

    ; // PINE - Input Pins, Port E
    public static final int	PINE0	= 0	; // 
    public static final int	PINE1	= 1	; // 
    public static final int	PINE2	= 2	; // 
    public static final int	PINE3	= 3	; // 
    public static final int	PINE4	= 4	; // 
    public static final int	PINE5	= 5	; // 
    public static final int	PINE6	= 6	; // 
    public static final int	PINE7	= 7	; // 


    ; // ***** PORTF ************************
    ; // PORTF - Data Register, Port F
    public static final int	PORTF0	= 0	; // 
    public static final int	PF0	= 0	; // For compatibility
    public static final int	PORTF1	= 1	; // 
    public static final int	PF1	= 1	; // For compatibility
    public static final int	PORTF2	= 2	; // 
    public static final int	PF2	= 2	; // For compatibility
    public static final int	PORTF3	= 3	; // 
    public static final int	PF3	= 3	; // For compatibility
    public static final int	PORTF4	= 4	; // 
    public static final int	PF4	= 4	; // For compatibility
    public static final int	PORTF5	= 5	; // 
    public static final int	PF5	= 5	; // For compatibility
    public static final int	PORTF6	= 6	; // 
    public static final int	PF6	= 6	; // For compatibility
    public static final int	PORTF7	= 7	; // 
    public static final int	PF7	= 7	; // For compatibility

    ; // DDRF - Data Direction Register, Port F
    public static final int	DDF0	= 0	; // 
    public static final int	DDF1	= 1	; // 
    public static final int	DDF2	= 2	; // 
    public static final int	DDF3	= 3	; // 
    public static final int	DDF4	= 4	; // 
    public static final int	DDF5	= 5	; // 
    public static final int	DDF6	= 6	; // 
    public static final int	DDF7	= 7	; // 

    ; // PINF - Input Pins, Port F
    public static final int	PINF0	= 0	; // 
    public static final int	PINF1	= 1	; // 
    public static final int	PINF2	= 2	; // 
    public static final int	PINF3	= 3	; // 
    public static final int	PINF4	= 4	; // 
    public static final int	PINF5	= 5	; // 
    public static final int	PINF6	= 6	; // 
    public static final int	PINF7	= 7	; // 


    ; // ***** PORTG ************************
    ; // PORTG - Data Register, Port G
    public static final int	PORTG0	= 0	; // 
    public static final int	PG0	= 0	; // For compatibility
    public static final int	PORTG1	= 1	; // 
    public static final int	PG1	= 1	; // For compatibility
    public static final int	PORTG2	= 2	; // 
    public static final int	PG2	= 2	; // For compatibility
    public static final int	PORTG3	= 3	; // 
    public static final int	PG3	= 3	; // For compatibility
    public static final int	PORTG4	= 4	; // 
    public static final int	PG4	= 4	; // For compatibility

    ; // DDRG - Data Direction Register, Port G
    public static final int	DDG0	= 0	; // 
    public static final int	DDG1	= 1	; // 
    public static final int	DDG2	= 2	; // 
    public static final int	DDG3	= 3	; // 
    public static final int	DDG4	= 4	; // 

    ; // PING - Input Pins, Port G
    public static final int	PING0	= 0	; // 
    public static final int	PING1	= 1	; // 
    public static final int	PING2	= 2	; // 
    public static final int	PING3	= 3	; // 
    public static final int	PING4	= 4	; // 


    ; // ***** TIMER_COUNTER_0 **************
    ; // TCCR0 - Timer/Counter Control Register
    public static final int	CS00	= 0	; // Clock Select 0
    public static final int	CS01	= 1	; // Clock Select 1
    public static final int	CS02	= 2	; // Clock Select 2
    public static final int	WGM01	= 3	; // Waveform Generation Mode 1
    public static final int	CTC0	= WGM01	; // For compatibility
    public static final int	COM00	= 4	; // Compare match Output Mode 0
    public static final int	COM01	= 5	; // Compare Match Output Mode 1
    public static final int	WGM00	= 6	; // Waveform Generation Mode 0
    public static final int	PWM0	= WGM00	; // For compatibility
    public static final int	FOC0	= 7	; // Force Output Compare

    ; // TCNT0 - Timer/Counter Register
    public static final int	TCNT0_0	= 0	; // 
    public static final int	TCNT0_1	= 1	; // 
    public static final int	TCNT0_2	= 2	; // 
    public static final int	TCNT0_3	= 3	; // 
    public static final int	TCNT0_4	= 4	; // 
    public static final int	TCNT0_5	= 5	; // 
    public static final int	TCNT0_6	= 6	; // 
    public static final int	TCNT0_7	= 7	; // 

    ; // OCR0 - Output Compare Register
    public static final int	OCR0_0	= 0	; // 
    public static final int	OCR0_1	= 1	; // 
    public static final int	OCR0_2	= 2	; // 
    public static final int	OCR0_3	= 3	; // 
    public static final int	OCR0_4	= 4	; // 
    public static final int	OCR0_5	= 5	; // 
    public static final int	OCR0_6	= 6	; // 
    public static final int	OCR0_7	= 7	; // 

    ; // ASSR - Asynchronus Status Register
    public static final int	TCR0UB	= 0	; // Timer/Counter Control Register 0 Update Busy
    public static final int	OCR0UB	= 1	; // Output Compare register 0 Busy
    public static final int	TCN0UB	= 2	; // Timer/Counter0 Update Busy
    public static final int	AS0	= 3	; // Asynchronus Timer/Counter 0

    ; // TIMSK - Timer/Counter Interrupt Mask Register
    public static final int	TOIE0	= 0	; // Timer/Counter0 Overflow Interrupt Enable
    public static final int	OCIE0	= 1	; // Timer/Counter0 Output Compare Match Interrupt register

    ; // TIFR - Timer/Counter Interrupt Flag register
    public static final int	TOV0	= 0	; // Timer/Counter0 Overflow Flag
    public static final int	OCF0	= 1	; // Output Compare Flag 0


    ; // ***** TIMER_COUNTER_1 **************
    ; // TIMSK - Timer/Counter Interrupt Mask Register
    public static final int	TOIE1	= 2	; // Timer/Counter1 Overflow Interrupt Enable
    public static final int	OCIE1B	= 3	; // Timer/Counter1 Output CompareB Match Interrupt Enable
    public static final int	OCIE1A	= 4	; // Timer/Counter1 Output CompareA Match Interrupt Enable
    public static final int	TICIE1	= 5	; // Timer/Counter1 Input Capture Interrupt Enable

    ; // ETIMSK - Extended Timer/Counter Interrupt Mask Register
    public static final int	OCIE1C	= 0	; // Timer/Counter 1, Output Compare Match C Interrupt Enable

    ; // TIFR - Timer/Counter Interrupt Flag register
    public static final int	TOV1	= 2	; // Timer/Counter1 Overflow Flag
    public static final int	OCF1B	= 3	; // Output Compare Flag 1B
    public static final int	OCF1A	= 4	; // Output Compare Flag 1A
    public static final int	ICF1	= 5	; // Input Capture Flag 1

    ; // ETIFR - Extended Timer/Counter Interrupt Flag register
    public static final int	OCF1C	= 0	; // Timer/Counter 1, Output Compare C Match Flag

    ; // SFIOR - Special Function IO Register
    public static final int	PSR321	= 0	; // Prescaler Reset, T/C3, T/C2, T/C1
    public static final int	TSM	= 7	; // Timer/Counter Synchronization Mode

    ; // TCCR1A - Timer/Counter1 Control Register A
    public static final int	WGM10	= 0	; // Waveform Generation Mode Bit 0
    public static final int	PWM10	= WGM10	; // For compatibility
    public static final int	WGM11	= 1	; // Waveform Generation Mode Bit 1
    public static final int	PWM11	= WGM11	; // For compatibility
    public static final int	COM1C0	= 2	; // Compare Output Mode 1C, bit 0
    public static final int	COM1C1	= 3	; // Compare Output Mode 1C, bit 1
    public static final int	COM1B0	= 4	; // Compare Output Mode 1B, bit 0
    public static final int	COM1B1	= 5	; // Compare Output Mode 1B, bit 1
    public static final int	COM1A0	= 6	; // Compare Ouput Mode 1A, bit 0
    public static final int	COM1A1	= 7	; // Compare Output Mode 1A, bit 1

    ; // TCCR1B - Timer/Counter1 Control Register B
    public static final int	CS10	= 0	; // Clock Select bit 0
    public static final int	CS11	= 1	; // Clock Select 1 bit 1
    public static final int	CS12	= 2	; // Clock Select1 bit 2
    public static final int	WGM12	= 3	; // Waveform Generation Mode
    public static final int	CTC10	= WGM12	; // For compatibility
    public static final int	WGM13	= 4	; // Waveform Generation Mode
    public static final int	CTC11	= WGM13	; // For compatibility
    public static final int	ICES1	= 6	; // Input Capture 1 Edge Select
    public static final int	ICNC1	= 7	; // Input Capture 1 Noise Canceler

    ; // TCCR1C - Timer/Counter1 Control Register C
    public static final int	FOC1C	= 5	; // Force Output Compare for channel C
    public static final int	FOC1B	= 6	; // Force Output Compare for channel B
    public static final int	FOC1A	= 7	; // Force Output Compare for channel A


    ; // ***** TIMER_COUNTER_2 **************
    ; // TCCR2 - Timer/Counter Control Register
    public static final int	CS20	= 0	; // Clock Select
    public static final int	CS21	= 1	; // Clock Select
    public static final int	CS22	= 2	; // Clock Select
    public static final int	WGM21	= 3	; // Waveform Generation Mode
    public static final int	CTC2	= WGM21	; // For compatibility
    public static final int	COM20	= 4	; // Compare Match Output Mode
    public static final int	COM21	= 5	; // Compare Match Output Mode
    public static final int	WGM20	= 6	; // Wafeform Generation Mode
    public static final int	PWM2	= WGM20	; // For compatibility
    public static final int	FOC2	= 7	; // Force Output Compare

    ; // TCNT2 - Timer/Counter Register
    public static final int	TCNT2_0	= 0	; // Timer/Counter Register Bit 0
    public static final int	TCNT2_1	= 1	; // Timer/Counter Register Bit 1
    public static final int	TCNT2_2	= 2	; // Timer/Counter Register Bit 2
    public static final int	TCNT2_3	= 3	; // Timer/Counter Register Bit 3
    public static final int	TCNT2_4	= 4	; // Timer/Counter Register Bit 4
    public static final int	TCNT2_5	= 5	; // Timer/Counter Register Bit 5
    public static final int	TCNT2_6	= 6	; // Timer/Counter Register Bit 6
    public static final int	TCNT2_7	= 7	; // Timer/Counter Register Bit 7

    ; // OCR2 - Output Compare Register
    public static final int	OCR2_0	= 0	; // Output Compare Register Bit 0
    public static final int	OCR2_1	= 1	; // Output Compare Register Bit 1
    public static final int	OCR2_2	= 2	; // Output Compare Register Bit 2
    public static final int	OCR2_3	= 3	; // Output Compare Register Bit 3
    public static final int	OCR2_4	= 4	; // Output Compare Register Bit 4
    public static final int	OCR2_5	= 5	; // Output Compare Register Bit 5
    public static final int	OCR2_6	= 6	; // Output Compare Register Bit 6
    public static final int	OCR2_7	= 7	; // Output Compare Register Bit 7

    ; // TIMSK - 
    public static final int	TOIE2	= 6	; // 
    public static final int	OCIE2	= 7	; // 

    ; // TIFR - Timer/Counter Interrupt Flag Register
    public static final int	TOV2	= 6	; // Timer/Counter2 Overflow Flag
    public static final int	OCF2	= 7	; // Output Compare Flag 2


    ; // ***** TIMER_COUNTER_3 **************
    ; // ETIMSK - Extended Timer/Counter Interrupt Mask Register
    public static final int	OCIE3C	= 1	; // Timer/Counter3, Output Compare Match Interrupt Enable
    public static final int	TOIE3	= 2	; // Timer/Counter3 Overflow Interrupt Enable
    public static final int	OCIE3B	= 3	; // Timer/Counter3 Output CompareB Match Interrupt Enable
    public static final int	OCIE3A	= 4	; // Timer/Counter3 Output CompareA Match Interrupt Enable
    public static final int	TICIE3	= 5	; // Timer/Counter3 Input Capture Interrupt Enable

    ; // ETIFR - Extended Timer/Counter Interrupt Flag register
    public static final int	OCF3C	= 1	; // Timer/Counter3 Output Compare C Match Flag
    public static final int	TOV3	= 2	; // Timer/Counter3 Overflow Flag
    public static final int	OCF3B	= 3	; // Output Compare Flag 1B
    public static final int	OCF3A	= 4	; // Output Compare Flag 1A
    public static final int	ICF3	= 5	; // Input Capture Flag 1

    ; // SFIOR - Special Function IO Register
    ; //public static final int	PSR321	= 0	; // Prescaler Reset, T/C3, T/C2, T/C1
    ; //public static final int	PSR1	= PSR321	; // For compatibility
    ; //public static final int	PSR2	= PSR321	; // For compatibility
    ; //public static final int	TSM	= 7	; // Timer/Counter Synchronization Mode

    ; // TCCR3A - Timer/Counter3 Control Register A
    public static final int	WGM30	= 0	; // Waveform Generation Mode Bit 0
    public static final int	PWM30	= WGM30	; // For compatibility
    public static final int	WGM31	= 1	; // Waveform Generation Mode Bit 1
    public static final int	PWM31	= WGM31	; // For compatibility
    public static final int	COM3C0	= 2	; // Compare Output Mode 3C, bit 0
    public static final int	COM3C1	= 3	; // Compare Output Mode 3C, bit 1
    public static final int	COM3B0	= 4	; // Compare Output Mode 3B, bit 0
    public static final int	COM3B1	= 5	; // Compare Output Mode 3B, bit 1
    public static final int	COM3A0	= 6	; // Comparet Ouput Mode 3A, bit 0
    public static final int	COM3A1	= 7	; // Compare Output Mode 3A, bit 1

    ; // TCCR3B - Timer/Counter3 Control Register B
    public static final int	CS30	= 0	; // Clock Select 3 bit 0
    public static final int	CS31	= 1	; // Clock Select 3 bit 1
    public static final int	CS32	= 2	; // Clock Select3 bit 2
    public static final int	WGM32	= 3	; // Waveform Generation Mode
    public static final int	CTC30	= WGM32	; // For compatibility
    public static final int	WGM33	= 4	; // Waveform Generation Mode
    public static final int	CTC31	= WGM33	; // For compatibility
    public static final int	ICES3	= 6	; // Input Capture 3 Edge Select
    public static final int	ICNC3	= 7	; // Input Capture 3  Noise Canceler

    ; // TCCR3C - Timer/Counter3 Control Register C
    public static final int	FOC3C	= 5	; // Force Output Compare for channel C
    public static final int	FOC3B	= 6	; // Force Output Compare for channel B
    public static final int	FOC3A	= 7	; // Force Output Compare for channel A

    ; // TCNT3L - Timer/Counter3 Low Byte
    public static final int	TCN3L0	= 0	; // Timer/Counter 3 bit 0
    public static final int	TCN3L1	= 1	; // Timer/Counter 3 bit 1
    public static final int	TCN3L2	= 2	; // Timer/Counter 3 bit 2
    public static final int	TCN3L3	= 3	; // Timer/Counter 3 bit 3
    public static final int	TCN3L4	= 4	; // Timer/Counter 3 bit 4
    public static final int	TCN3L5	= 5	; // Timer/Counter 3 bit 5
    public static final int	TCN3L6	= 6	; // Timer/Counter 3 bit 6
    public static final int	TCN3L7	= 7	; // Timer/Counter 3 bit 7


    ; // ***** WATCHDOG *********************
    ; // WDTCR - Watchdog Timer Control Register
    public static final int	WDTCSR	= WDTCR	; // For compatibility
    public static final int	WDP0	= 0	; // Watch Dog Timer Prescaler bit 0
    public static final int	WDP1	= 1	; // Watch Dog Timer Prescaler bit 1
    public static final int	WDP2	= 2	; // Watch Dog Timer Prescaler bit 2
    public static final int	WDE	= 3	; // Watch Dog Enable
    public static final int	WDCE	= 4	; // Watchdog Change Enable
    public static final int	WDTOE	= WDCE	; // For compatibility



    ; // ***** LOCKSBITS ********************************************************
    public static final int	LB1	= 0	; // Lock bit
    public static final int	LB2	= 1	; // Lock bit
    public static final int	BLB01	= 2	; // Boot Lock bit
    public static final int	BLB02	= 3	; // Boot Lock bit
    public static final int	BLB11	= 4	; // Boot lock bit
    public static final int	BLB12	= 5	; // Boot lock bit


    ; // ***** FUSES ************************************************************
    ; // LOW fuse bits
    public static final int	CKSEL0	= 0	; // Select Clock Source
    public static final int	CKSEL1	= 1	; // Select Clock Source
    public static final int	CKSEL2	= 2	; // Select Clock Source
    public static final int	CKSEL3	= 3	; // Select Clock Source
    public static final int	SUT0	= 4	; // Select start-up time
    public static final int	SUT1	= 5	; // Select start-up time
    public static final int	BODEN	= 6	; // Brown out detector enable
    public static final int	BODLEVEL	= 7	; // Brown out detector trigger level

    ; // HIGH fuse bits
    public static final int	BOOTRST	= 0	; // Select Reset Vector
    public static final int	BOOTSZ0	= 1	; // Select Boot Size
    public static final int	BOOTSZ1	= 2	; // Select Boot Size
    public static final int	EESAVE	= 3	; // EEPROM memory is preserved through chip erase
    public static final int	CKOPT	= 4	; // Oscillator Options
    public static final int	SPIEN	= 5	; // Enable Serial programming and Data Downloading
    public static final int	JTAGEN	= 6	; // Enable JTAG
    public static final int	OCDEN	= 7	; // Enable OCD

    ; // EXTENDED fuse bits
    public static final int	WDTON	= 0	; // Watchdog timer always on
    public static final int	CompMode	= 1	; // Compabillity mode



    ; // ***** CPU REGISTER DEFINITIONS *****************************************
    public static final int	XH	= r27;
    public static final int	XL	= r26;
    public static final int	YH	= r29;
    public static final int	YL	= r28;
    public static final int	ZH	= r31;
    public static final int	ZL	= r30;



    ; // ***** DATA MEMORY DECLARATIONS *****************************************
    public static final int	FLASHEND	= 0x7fff	; // Note: Word address
    public static final int	IOEND	= 0x00ff;
    public static final int	SRAM_START	= 0x0100;
    public static final int	SRAM_SIZE	= 4096;
    public static final int	RAMEND	= 0x10ff;
    public static final int	XRAMEND	= 0xffff;
    public static final int	E2END	= 0x07ff;
    public static final int	EEPROMEND	= 0x07ff;
    public static final int	EEADRBITS	= 11;


    ; // ***** BOOTLOADER DECLARATIONS ******************************************
    public static final int	NRWW_START_ADDR	= 0x7000;
    public static final int	NRWW_STOP_ADDR	= 0x7fff;
    public static final int	RWW_START_ADDR	= 0x0;
    public static final int	RWW_STOP_ADDR	= 0x6fff;
    public static final int	PAGESIZE	= 128;
    public static final int	FIRSTBOOTSTART	= 0x7e00;
    public static final int	SECONDBOOTSTART	= 0x7c00;
    public static final int	THIRDBOOTSTART	= 0x7800;
    public static final int	FOURTHBOOTSTART	= 0x7000;
    public static final int	SMALLBOOTSTART	= FIRSTBOOTSTART;
    public static final int	LARGEBOOTSTART	= FOURTHBOOTSTART;



    ; // ***** INTERRUPT VECTORS ************************************************
    public static final int	INT0addr	= 0x0002	; // External Interrupt Request 0
    public static final int	INT1addr	= 0x0004	; // External Interrupt Request 1
    public static final int	INT2addr	= 0x0006	; // External Interrupt Request 2
    public static final int	INT3addr	= 0x0008	; // External Interrupt Request 3
    public static final int	INT4addr	= 0x000a	; // External Interrupt Request 4
    public static final int	INT5addr	= 0x000c	; // External Interrupt Request 5
    public static final int	INT6addr	= 0x000e	; // External Interrupt Request 6
    public static final int	INT7addr	= 0x0010	; // External Interrupt Request 7
    public static final int	OC2addr	= 0x0012	; // Timer/Counter2 Compare Match
    public static final int	OVF2addr	= 0x0014	; // Timer/Counter2 Overflow
    public static final int	ICP1addr	= 0x0016	; // Timer/Counter1 Capture Event
    public static final int	OC1Aaddr	= 0x0018	; // Timer/Counter1 Compare Match A
    public static final int	OC1Baddr	= 0x001a	; // Timer/Counter Compare Match B
    public static final int	OVF1addr	= 0x001c	; // Timer/Counter1 Overflow
    public static final int	OC0addr	= 0x001e	; // Timer/Counter0 Compare Match
    public static final int	OVF0addr	= 0x0020	; // Timer/Counter0 Overflow
    public static final int	SPIaddr	= 0x0022	; // SPI Serial Transfer Complete
    public static final int	URXC0addr	= 0x0024	; // USART0, Rx Complete
    public static final int	UDRE0addr	= 0x0026	; // USART0 Data Register Empty
    public static final int	UTXC0addr	= 0x0028	; // USART0, Tx Complete
    public static final int	ADCCaddr	= 0x002a	; // ADC Conversion Complete
    public static final int	ERDYaddr	= 0x002c	; // EEPROM Ready
    public static final int	ACIaddr	= 0x002e	; // Analog Comparator
    public static final int	OC1Caddr	= 0x0030	; // Timer/Counter1 Compare Match C
    public static final int	ICP3addr	= 0x0032	; // Timer/Counter3 Capture Event
    public static final int	OC3Aaddr	= 0x0034	; // Timer/Counter3 Compare Match A
    public static final int	OC3Baddr	= 0x0036	; // Timer/Counter3 Compare Match B
    public static final int	OC3Caddr	= 0x0038	; // Timer/Counter3 Compare Match C
    public static final int	OVF3addr	= 0x003a	; // Timer/Counter3 Overflow
    public static final int	URXC1addr	= 0x003c	; // USART1, Rx Complete
    public static final int	UDRE1addr	= 0x003e	; // USART1, Data Register Empty
    public static final int	UTXC1addr	= 0x0040	; // USART1, Tx Complete
    public static final int	TWIaddr	= 0x0042	; // 2-wire Serial Interface
    public static final int	SPMRaddr	= 0x0044	; // Store Program Memory Read

    public static final int	INT_VECTORS_SIZE	= 70	; // size in words
}
