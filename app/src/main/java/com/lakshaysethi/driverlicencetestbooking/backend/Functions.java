����   9 �
      java/lang/Object <init> ()V  please enter licenceNumber
 
     javax/swing/JOptionPane showInputDialog &(Ljava/lang/Object;)Ljava/lang/String;
      test/Functions createNewUser )(Ljava/lang/String;)Ltest/Functions$User;	     users Ljava/util/ArrayList;
      java/util/ArrayList add (Ljava/lang/Object;)Z   
2020/04/11 " 09:00 $ 2020/04/11 09:00
  & ' ( openShop )(Ljava/lang/String;)Ljava/util/ArrayList;
  * + , slot_available ;(Ljava/lang/String;Ljava/lang/String;)Ltest/Functions$Slot;
  . / 0 bookTimeSlot <(Ltest/Functions$User;Ljava/lang/String;Ljava/lang/String;)V
  2 3  showNotBookedMessage
  5 6 7 printAllBookings (Ljava/util/ArrayList;)V	 9 : ; < = java/lang/System out Ljava/io/PrintStream; ? List of all bookings
 A B C D E java/io/PrintStream println (Ljava/lang/String;)V
  G H I size ()I
  K L M get (I)Ljava/lang/Object; O test/Functions$User	 N Q R S licenceNumber Ljava/lang/String;   U V W makeConcatWithConstants &(Ljava/lang/String;)Ljava/lang/String;	 N Y Z  bookingsList \ test/Functions$Booking	 [ ^ _ S refNo  U	 [ b c d slot Ltest/Functions$Slot;
 f g h i j test/Functions$Slot toString ()Ljava/lang/String; l 
Not Booked
  	  o p  	slotsList r java/text/SimpleDateFormat t yyyy/MM/dd HH:mm
 q v  E
 q x y z parse $(Ljava/lang/String;)Ljava/util/Date; | java/text/ParseException
 { ~   printStackTrace
 f �  � (Ljava/util/Date;)V
 � � � � � java/util/Calendar getInstance ()Ljava/util/Calendar;
 � � � � setTime
 � �  � (II)V
 � � � � getTime ()Ljava/util/Date;
 [ �  � (Ltest/Functions$Slot;)V  � V � 8(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;	 f � � � date Ljava/util/Date;
 � � � �  java/util/Date equals	 f � � � remainingTimes I
 N v 	Signature ,Ljava/util/ArrayList<Ltest/Functions$Slot;>; ,Ljava/util/ArrayList<Ltest/Functions$User;>; Code LineNumberTable LocalVariableTable this Ltest/Functions; main ([Ljava/lang/String;)V args [Ljava/lang/String; u1 Ltest/Functions$User; day hour startDateString LocalVariableTypeTable StackMapTable � � java/lang/String j i /(Ljava/util/ArrayList<Ltest/Functions$User;>;)V e Ljava/text/ParseException; cal Ljava/util/Calendar; sdf Ljava/text/SimpleDateFormat; 	startDate dateForSlot @(Ljava/lang/String;)Ljava/util/ArrayList<Ltest/Functions$Slot;>; selectedSlot 
newBooking Ltest/Functions$Booking; testSlot correctString dateToCheck <clinit> 
SourceFile Functions.java NestMembers BootstrapMethods �
 � � � V � $java/lang/invoke/StringConcatFactory �(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite; � for  �        �   InnerClasses User Slot Booking � %java/lang/invoke/MethodHandles$Lookup � java/lang/invoke/MethodHandles Lookup !      	 p   �    � 	    �    � 	     �   /     *� �    �       	 �        � �   	 � �  �       B� 	L+� M� ,� WN!:#:� %:-� )� ,-� -� � 1� � 4�    �   2                 %  .  8  ;   A " �   H    B � �    < R S   7 � �   , � S   ( � S   $ � S  %  p   �     %  p �  �    � 8  � � N � � �    
 6 7  �       �� 8>� @<*� F� |� 8*� J� N� P� T  � @=*� J� N� X� F� L� 8*� J� N� X� J� [� ]� `  � @� 8*� J� N� X� J� [� a� e� `  � @���������    �   & 	   %  &  ' ( ( < ) \ *  ( � & � . �      * [ � �  
 � � �    �     �       �  �   �    � 
� � Z�  �    � 
 3   �   %      	� 8k� @�    �   
    1  2 
 ' (  �  2     b� Y� m� n� qYs� uLM+*� wM� N-� },N6(� 1� n� fY-� �� W� �:-� �� �� �N���β n�     {  �   B    6 
 8  9  ;  >  <   = $ ? & A 0 C ? F D G J H R I X A ^ P �   H     � �  D  � �  ) 5 � �    b � S    N � �   L � �  & < � �  �     �   � q �  {�  �� 4 �    � 
 / 0  �   y     +,� )N� [Y-� �:*� X� W�    �       T  U  W  [ �   4     � �      � S     � S    � d    � �  
 + ,  �  G     m*+� �  M� qYs� uN:-,� w:� 
:� }6� n� F� :� n� J� f:� �� �� � �� Y� �d� �������     {  �   >    ^  `  a  c  f  d ! e & i 4 j A k N l W m b n e i k s �   R  !  � �  A $ � d  ) B � �    m � S     m � S   e � S   [ � �   X � �  �   $ �   � � � q �  {� ;�  
    �   3     	� NY*� ��    �       w �       	 R S    �   �   #      � Y� m� �    �         �    � �     [ N f �     �  � �  � �  � �   "  N  � 	 f  � 	 [  � 	 � � � 