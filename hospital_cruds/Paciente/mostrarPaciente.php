<?php
include('../conexion.php');
$link=conectar();
$res=array();
$res['datos']=array();
$sql="select * from paciente";
$resu=mysqli_query($link,$sql);
while($row=mysqli_fetch_array($resu)){
$i['CC_Paciente'] = $row[0];
$i['Nombre_Paciente'] = $row[1];
$i['Apellido_Paciente'] = $row[2];
$i['Id_Genero'] = $row[3];
$i['Fecha_Nac'] = $row[4];
$i['Id_RH'] = $row[5];
$i['Contraseña'] = $row[6];
array_push($res['datos'],$i);
}
$res["succes"]="1";
echo json_encode($res);
mysqli_close($link);
?>