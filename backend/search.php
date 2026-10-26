<?php
require_once __DIR__ . '/conn.php';

connect();

$C = $_POST["C"];
$D1 = $_POST["D1"];
$D2 = $_POST["D2"];
$D3 = $_POST["D3"];
$D4 = $_POST["D4"];
$D5 = $_POST["D5"];
$D6 = $_POST["D6"];
$D7 = $_POST["D7"];
$D8 = $_POST["D8"];
$D9 = $_POST["D9"];
$D10 = $_POST["D10"];
$D11 = $_POST["D11"];
$D12 = $_POST["D12"];
$D13 = $_POST["D13"];
$D14 = $_POST["D14"];
$D15 = $_POST["D15"];
$D16 = $_POST["D16"];
$D17 = $_POST["D17"];
$D18 = $_POST["D18"];
$D19 = $_POST["D19"];
$D20 = $_POST["D20"];
$D21 = $_POST["D21"];
$S1 = $_POST["S1"];
$S2 = $_POST["S2"];
$S3 = $_POST["S3"];
$S4 = $_POST["S4"];
$S5 = $_POST["S5"];
$S6 = $_POST["S6"];
$S7 = $_POST["S7"];
$S8 = $_POST["S8"];
$S9 = $_POST["S9"];
$S10 = $_POST["S10"];
$S11 = $_POST["S11"];
$S12 = $_POST["S12"];
$S13 = $_POST["S13"];
$S14 = $_POST["S14"];
$S15 = $_POST["S15"];
$S16 = $_POST["S16"];
$S17 = $_POST["S17"];
$S18 = $_POST["S18"];
$S19 = $_POST["S19"];
$M1 = $_POST["M1"];
$M2 = $_POST["M2"];
$M3 = $_POST["M3"];
$M4 = $_POST["M4"];
$P1 = $_POST["P1"];
$P2 = $_POST["P2"];
$P3 = $_POST["P3"];
$datas = array();
$mysql_qry = "select distinct restaurant.ID, Name from restaurant, style, payment where (restaurant.ID = style.ID) and (restaurant.ID = payment.ID) and (City = '$C') and 
(District = '$D1' or District = '$D2' or District = '$D3' or District = '$D4' or District = '$D5' or District = '$D6' or District = '$D7' or District = '$D8' or District = '$D9' or District = '$D10' or District = '$D11' or District = '$D12' or District = '$D13' or District = '$D14' or District = '$D15' or District = '$D16' or District = '$D17' or District = '$D18' or District = '$D19' or District = '$D20' or District = '$D21') and
(style = '$S1' or style = '$S2' or style = '$S3' or style = '$S4' or style = '$S5' or style = '$S6' or style = '$S7' or style = '$S8' or style = '$S9' or style = '$S10' or style = '$S11' or style = '$S12' or style = '$S13' or style = '$S14' or style = '$S15' or style = '$S16' or style = '$S17' or style = '$S18' or style = '$S19') and
(Meal = '$M1' or Meal = '$M2' or Meal = '$M3' or Meal = '$M4') and
(payment = '$P1' or payment = '$P2' or payment = '$P3');";
$result = mysqli_query($con, $mysql_qry);
while ($row = mysqli_fetch_assoc($result)) {
       $datas[] = $row;
}	

print(json_encode($datas,JSON_UNESCAPED_UNICODE));
close();

?>