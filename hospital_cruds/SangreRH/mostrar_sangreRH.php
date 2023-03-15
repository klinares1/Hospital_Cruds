<?php
include('../conexion.php');
$link=conectar();
$res=array();
$res['datos']=array();
$sql="select * from sangre_RH";
$resu=mysqli_query($link,$sql);
while($row=mysqli_fetch_array($resu)){
$i['Id_RH'] = $row[0];
$i['Nombre_RH'] = $row[1];
array_push($res['datos'],$i);
}
$res["succes"]="1";
echo json_encode($res);
mysqli_close($link);
?>