# JLD806_Android
JLD806安卓主板的二次开发，通过厂商提供的SDK查询并设置GPIO口的高低电平
##作用
通过厂商的SDK读写IO口
接口使用:
     
(1)如要读取某个IO口的状态，调用接口: int Gpio.readGpio(String io_name); 
 
     example: 如要读取io1,代码如下。1: 表示高电平, 0: 表示低电平.
              int gpio_status = Gpio.GetGpioValue("gpio1");
			  if (gpio_status == 1) {

					tvResult.setText(String.valueOf(editNum.getText()) + "->读取高电平完 
                 成");
					
				} else {

					tvResult.setText(String.valueOf(editNum.getText()) + "->读取低电平完
                 成");
					
				}

 (2)如要写入某个IO口的状态，调用接口: int Gpio.SetGpioOutputHigh (String io_name); 

     example: 如要gpio1输出高电平，代码如下：
                
			 	Gpio.SetGpioOutputHigh ("gpio1");    

(3)JLD802/JLD806主板共有5个gpio口, 可作为输入或输出口, gpio口访问名字如下图.
    备注: gpio口名字都为英文小写.
    
##背光控制

设置背光开：
	Gpio.SetGpioOutputHigh("gpio_bl");
	
   	try {
           Thread.sleep(500);
              
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
      
    Gpio.SetGpioOutputHigh("gpio_lcd_en");


设置背光关：

Gpio.SetGpioOutputLow("gpio_bl");
Gpio.SetGpioOutputLow("gpio_lcd_en");



##开关机控制

//Function:  设置主板RTC硬件时钟.
//Parameter: set_value -> 时间参数的数组，数组长度为6.
//           日期和时间 [0]:年, [1]:月，[2]:日，[3]:时，[4]:分，[5]:秒
//Return:    true -> 成功    false -> 失败。
//
public synchronized boolean set_mcu_time(int[] set_value)


//---------------------------------------------------------------------------//
//Function:  读取主板RTC硬件时钟.
//Parameter: 无
//Return:    成功-> 返回一个数组，数组长度为6.
//           日期和时间 [0]:年, [1]:月，[2]:日，[3]:时，[4]:分，[5]:秒，
//
//           失败-> 返回一个数组，数组长度为1, 值为－1.
//
public synchronized int[] get_mcu_time()


//---------------------------------------------------------------------------//
//Function:  设置指定日期定时开/关机日期时间
//Parameter: set_value -> 时间参数的数组，数组长度为10.
//           开机日期和时间 [0]:年, [1]:月，[2]:日，[3]:时，[4]:分，
//           关机日期和时间 [5]:年, [6]:月，[7]:日，[8]:时，[9]:分，
//Return:    true -> 成功    false -> 失败。
//
public synchronized boolean set_onoff_by_day(int[] set_value)



//Function:  读取当前定时开关机设置
//Parameter: 无
//Return:    成功-> 返回一个数组，数组长度为10.
//           开机日期和时间 [0]:年, [1]:月，[2]:日，[3]:时，[4]:分，
//           关机日期和时间 [5]:年, [6]:月，[7]:日，[8]:时，[9]:分，
//
//           失败-> 返回一个数组，数组长度为1, 值为－1.
//
public synchronized int[]  get_onoff_by_day()



//Function:  启动或停止指定日期的定时开关机设置。
//Parameter: enalbe -> 1: 启动，  0: 停止
//Return:    true -> 成功    false -> 失败。
//
public synchronized  boolean  enable_onoff_by_day(int enable)



//Function:  判断当前是否启动指定日期的定时开关机设置。
//Parameter: 无
//Return:    成功  1:已启动， 0:未启动
//           失败 －1: 读取失败
public  synchronized int check_onoff_by_day()

//-----------------------------------------------------------------------------//



//-----------------------------------------------------------------------------//
//Function:  系统立即关机。
//Parameter:  无
//Return:     true -> 成功    false -> 失败。
//
public synchronized boolean shutdown_system()


//Function:  系统立即重启
//Parameter:  无
//Return:     true -> 成功    false -> 失败。
//
public synchronized boolean restart_system()
//----------------------------------------------------------------------------//


接口使用:
(1)按指定日期设置定时开关机。
第一步：调用函数enable_onoff_by_day()取消先前的设置,enable: 0.
第二步：调用函数set_onoff_by_day()设置开机的时间日期和关机的时间日期。
第三步：调用函数enable_onoff_by_day()启动按日期开关机功能，enable: 1.


(2)注意事项：
(1)开机时间和关机时间，必须间隔3分钟以上。
(2)关机时间必须在开机时间前面, 即正常执行流程: 先关机,再开机.




