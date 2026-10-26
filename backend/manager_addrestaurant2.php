<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$ID = $object->{'ID'};
$Name = $object->{'Name'};
$Address = $object->{'Address'};
$City = $object->{'City'};
$District = $object->{'District'};
$Phone = $object->{'Phone'};
$Price = $object->{'Price'};
$BusinessHours = $object->{'BusinessHours'};
$WiFi = $object->{'WiFi'};
$Accessibility = $object->{'Accessibility'};
$Delivery = $object->{'Delivery'};
$URL = $object->{'URL'};
$FanPage = $object->{'FanPage'};
$picurl = $object->{'picurl'};

if(isset($ID) || isset($Name) || isset($Address) || isset($City) || isset($District) || isset($Phone) || isset($Price) || isset($BusinessHours) || isset($WiFi) || isset($Accessibility) || isset($Delivery) || isset($URL) || isset($FanPage) || isset($picurl)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "INSERT INTO restaurant(ID, Name, Address, District, City, Phone, Price, WiFi, Accessibility, Delivery, URL, FanPage, BusinessHours, pictureURL) VALUES('$ID', '$Name', '$Address', '$District', '$City', '$Phone', '$Price', '$WiFi', '$Accessibility', '$Delivery', '$URL', '$FanPage', '$BusinessHours', '$picurl')");
}

?>