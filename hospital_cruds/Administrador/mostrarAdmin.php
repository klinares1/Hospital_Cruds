<?php
include('../conexion.php');
$link=conectar();
$res=array();
$res['datos']=array();
$sql="select * from administrador";
$resu=mysqli_query($link,$sql);
while($row=mysqli_fetch_array($resu)){
$i['CC_Administrador'] = $row[0];
$i['Nombre_Admin'] = $row[1];
$i['Apellido_Admin'] = $row[2];
$i['Contraseña_Admin'] = $row[3];
array_push($res['datos'],$i);
}
$res["succes"]="1";
echo json_encode($res);
mysqli_close($link);
?>