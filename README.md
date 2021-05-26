# Description

An intelligent image backdrop expelling toolkit for artificial intelligence. you can use this to deal with images, 

such as clean their background so that fetch the main area of the image. In this way to promote quality of AI 

training fodder.

There is undergo a lot of testing, fit in major normal image backdrop expelling, but not all image. It is not

proper to process too simple or too complex or too small images. the too simple image meaning that only 

few pigment on it, the too complex image indicate that so many pigment on it so that no main area can found 

on the image, the too small image imply that it's size less then 256x256.

This project quote one image deal with toolkit called 'Thumbnails', there is no jar package found on maven central 

repository, so we upload it's code to the github.


# Backdrop Expelling

Here are some examples of backdrop expelling. the color image is source image, and the gray image are the 

respectively target image.

<table>

<tr style="vertical-align: top;">

<td>
<table style="text-align:center;">
<tr style="background-color: #ccc;"><td>Source</td><td>Expelling</td></tr>
<tr><td>104kb</td><td>3.48kb</td></tr>
<tr><td>1920x1080</td><td>93x144</td></tr>
<tr>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/01.jpg" width="128px"/></td>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/01-1.jpg" width="128px"/></td>
</tr>
</table>
</td>

<td>
<table style="text-align:center;">
<tr style="background-color: #ccc;"><td>Source</td><td>Expelling</td></tr>
<tr><td>84.8kb</td><td>6.87kb</td></tr>
<tr><td>900x1150</td><td>197x256</td></tr>
<tr>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/02.jpg" width="128px"/></td>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/02-1.jpg" width="128px"/></td>
</tr>
</table>
</td>


<td>
<table style="text-align:center;">
<tr style="background-color: #ccc;"><td>Source</td><td>Expelling</td></tr>
<tr><td>57.9kb</td><td>6.12kb</td></tr>
<tr><td>709x1024</td><td>162x256</td></tr>
<tr>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/03.jpg" width="128px"/></td>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/03-1.jpg" width="128px"/></td>
</tr>
</table>
</td>

</tr>

<tr style="vertical-align: top;">

<td>
<table style="text-align:center;">
<tr style="background-color: #ccc;"><td>Source</td><td>Expelling</td></tr>
<tr><td>74.6kb</td><td>9.16kb</td></tr>
<tr><td>1024x768</td><td>220x192</td></tr>
<tr>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/07.jpg" width="128px"/></td>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/07-1.jpg" width="128px"/></td>
</tr>
</table>
</td>

<td>
<table style="text-align:center;">
<tr style="background-color: #ccc;"><td>Source</td><td>Expelling</td></tr>
<tr><td>110kb</td><td>10.8kb</td></tr>
<tr><td>1024x768</td><td>216x192</td></tr>
<tr>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/08.jpg" width="128px"/></td>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/08-1.jpg" width="128px"/></td>
</tr>
</table>
</td>


<td>
<table style="text-align:center;">
<tr style="background-color: #ccc;"><td>Source</td><td>Expelling</td></tr>
<tr><td>320kb</td><td>6.17kb</td></tr>
<tr><td>1826x1027</td><td>146x144</td></tr>
<tr>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/09.jpg" width="128px"/></td>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/09-1.jpg" width="128px"/></td>
</tr>
</table>
</td>

</tr>

<tr style="vertical-align: top;">

<td>
<table style="text-align:center;">
<tr style="background-color: #ccc;"><td>Source</td><td>Expelling</td></tr>
<tr><td>87.1kb</td><td>15.5kb</td></tr>
<tr><td>1024x714</td><td>256x179</td></tr>
<tr>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/04.jpg" width="128px"/></td>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/04-1.jpg" width="128px"/></td>
</tr>
</table>
</td>

<td>
<table style="text-align:center;">
<tr style="background-color: #ccc;"><td>Source</td><td>Expelling</td></tr>
<tr><td>83.8kb</td><td>4.41kb</td></tr>
<tr><td>1024x768</td><td>103x192</td></tr>
<tr>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/05.jpg" width="128px"/></td>
<td style="vertical-align: top;">
<img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/05-1.jpg" width="128px"/></td>
</tr>
</table>
</td>


<td>
<table style="text-align:center;">
<tr style="background-color: #ccc;"><td>Source</td><td>Expelling</td></tr>
<tr><td>57.9kb</td><td>5.45kb</td></tr>
<tr><td>1024x640</td><td>135x160</td></tr>
<tr>
<td style="vertical-align: top;">
  <img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/06.jpg" width="128px"/></td>
<td style="vertical-align: top;">
  <img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/06-1.jpg" width="128px"/></td>
</tr>
</table>
</td>

</tr>

</table>

# Derivation

We also developed one algorithm of image recognition, It's purpose is to enhance the nerve cumulus.

The lately version has high performance and accuracy, It is face to image classification and machine 

learning. But it is unmatured to open code, the following is the preview of the algorithm. 


# AI Proposition

General (allowed mistake) universe algorithm for artificial intelligence. It deem that image recognition should

be no direction, so that can recognize the rotated with center or upside-down image base on source image.

It's mainly trait is less training but more recognition, if more training your are gave, than more accuracy it show. 

# AI effects

If training the rightmost image of the table, than can recognize all of the left images. If using the left images of 

the table as training, of course will recognize more image like this.

<p><b>example 1</b></p>

<table style="text-align:center;">
<tr style="background-color: #ccc;">
<td style="text-align:right;"><b>Length: </b></td><td>362kb</td><td>340kb</td><td>431kb</td><td>427kb</td><td></td><td>320kb</td>
</tr>
<tr style="background-color: #ccc;">
<td style="text-align:right;"><b>Size: </b></td><td>1027x1492</td><td>1492x1027</td><td>1826x1027</td><td>1826x1027</td><td></td><td>1826x1027</td>
</tr>
<tr>
<td style="text-align:right;background-color: #ccc;"><b>Image: </b></td>
<td><img src="https://raw.githubusercontent.com/Jazping/images/9bfdd7db59f8b8df3d79356cb07eace4003501b3/20210526/09-6.jpg" width="128px"/></td>
<td><img src="https://raw.githubusercontent.com/Jazping/images/9bfdd7db59f8b8df3d79356cb07eace4003501b3/20210526/09-1.jpg" width="128px"/></td>
<td><img src="https://raw.githubusercontent.com/Jazping/images/9bfdd7db59f8b8df3d79356cb07eace4003501b3/20210526/09-2.jpg" width="128px"/></td>
<td><img src="https://raw.githubusercontent.com/Jazping/images/9bfdd7db59f8b8df3d79356cb07eace4003501b3/20210526/09-3.jpg" width="128px"/></td>
<td><span>&lt;==&gt;</span></td>
<td><img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/09.jpg" width="128px"/></td>
</tr>
</table>

<p><b>example 2</b></p>

<table style="text-align:center;">
<tr style="background-color: #ccc;">
<td style="text-align:right;"><b>Length: </b></td><td>105kb</td><td>224kb</td><td>106kb</td><td>161kb</td><td></td><td>104kb</td>
</tr>
<tr style="background-color: #ccc;">
<td style="text-align:right;"><b>Size: </b></td><td>783x1041</td><td>1350x1646</td><td>783x1041</td><td>1566x1041</td><td></td><td>1920&1080</td>
</tr>
<tr>
<td style="text-align:right;background-color: #ccc;"><b>Image: </b></td>
<td><img src="https://raw.githubusercontent.com/Jazping/images/9bfdd7db59f8b8df3d79356cb07eace4003501b3/20210526/01-3.jpg" width="128px"/></td>
<td><img src="https://raw.githubusercontent.com/Jazping/images/9bfdd7db59f8b8df3d79356cb07eace4003501b3/20210526/01-7.jpg" width="128px"/></td>
<td><img src="https://raw.githubusercontent.com/Jazping/images/9bfdd7db59f8b8df3d79356cb07eace4003501b3/20210526/01-5.jpg" width="128px"/></td>
<td><img src="https://raw.githubusercontent.com/Jazping/images/9bfdd7db59f8b8df3d79356cb07eace4003501b3/20210526/01-8.jpg" width="128px"/></td>
<td><span>&lt;==&gt;</span></td>
<td><img src="https://raw.githubusercontent.com/Jazping/images/4de0261aef634e73b67bb4488f6efb611e76bd4d/20210526/01.jpg" alt="image not found" width="128px"/></td>
</tr>
</table>

# Get start

com.fusion.marvel.Gistspoter is the usage demo of this project. must fixate the directory for saving before running
