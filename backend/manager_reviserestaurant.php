<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$ID = $object->{'ID1'};
$Name = $object->{'Name1'};
$Address = $object->{'Address1'};
$City = $object->{'City1'};
$District = $object->{'District1'};
$Phone = $object->{'Phone1'};
$Price = $object->{'Price1'};
$BusinessHours = $object->{'BusinessHours1'};
$Meal = $object->{'Meal1'};
$WiFi = $object->{'WiFi1'};
$Accessibility = $object->{'Accessibility1'};
$Delivery = $object->{'Delivery1'};
$URL = $object->{'URL1'};
$FanPage = $object->{'FanPage1'};
$picurl = $object->{'picurl1'};

if(isset($ID) || isset($Name) || isset($Address) || isset($City) || isset($District) || isset($Phone) || isset($Price) || isset($BusinessHours) || isset($Meal) || isset($WiFi) || isset($Accessibility) || isset($Delivery) || isset($URL) || isset($FanPage) || isset($picurl)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "UPDATE restaurant SET Name = '$Name', Address = '$Address', District = '$District', City = '$City', Phone = '$Phone', Price = '$Price', WiFi = '$WiFi', Accessibility = '$Accessibility', Delivery = '$Delivery', URL = '$URL', FanPage = '$FanPage', BusinessHours = '$BusinessHours', Meal = '$Meal', pictureURL = '$picurl' WHERE ID = '$ID'");
}

?>