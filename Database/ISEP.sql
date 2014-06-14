-- phpMyAdmin SQL Dump
-- version 3.3.9.2
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Sam 14 Juin 2014 à 19:27
-- Version du serveur: 5.5.9
-- Version de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `ISEP`
--

-- --------------------------------------------------------

--
-- Structure de la table `competence`
--

CREATE TABLE `competence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_eleve` int(11) NOT NULL,
  `id_tut` int(11) NOT NULL,
  `name` text NOT NULL,
  `note` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `competence`
--

INSERT INTO `competence` VALUES(1, 3, 1, 'Travailler en équipe', '15');
INSERT INTO `competence` VALUES(2, 3, 1, 'Animer une équipe', '18');
INSERT INTO `competence` VALUES(3, 3, 1, 'Gérer les conflits', '14');
INSERT INTO `competence` VALUES(4, 3, 1, 'Etre force de proposition', '12');
INSERT INTO `competence` VALUES(5, 3, 1, 'Mener un dialogue', '12');
INSERT INTO `competence` VALUES(6, 4, 1, 'Travailler en équipe', NULL);
INSERT INTO `competence` VALUES(7, 4, 1, 'Animer une équipe', NULL);
INSERT INTO `competence` VALUES(8, 4, 1, 'Gérer les conflits', NULL);
INSERT INTO `competence` VALUES(9, 4, 1, 'Etre force de proposition', NULL);
INSERT INTO `competence` VALUES(10, 4, 1, 'Mener un dialogue', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE `cours` (
  `name` varchar(30) NOT NULL,
  `ID_TUTEUR` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `cours`
--

INSERT INTO `cours` VALUES('APP INFORMATIQUE', 1);
INSERT INTO `cours` VALUES('WEB AVANCE', 2);

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL DEFAULT '0',
  `lastname` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `office` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `employee`
--

INSERT INTO `employee` VALUES(0, 'li', 'zz', 65431, '12E');
INSERT INTO `employee` VALUES(1, 'LI', 'PEI', 123456, '22E');

-- --------------------------------------------------------

--
-- Structure de la table `EVALUATION`
--

CREATE TABLE `EVALUATION` (
  `ID_EVA` int(10) NOT NULL,
  `ID_ELEVE` int(10) NOT NULL,
  `ID_TUTEUR` int(10) NOT NULL,
  `NOM_COURS` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `EVALUATION`
--


-- --------------------------------------------------------

--
-- Structure de la table `relation`
--

CREATE TABLE `relation` (
  `id_tuteur` int(11) NOT NULL,
  `ID_ELEVE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `relation`
--

INSERT INTO `relation` VALUES(1, 3);
INSERT INTO `relation` VALUES(1, 4);
INSERT INTO `relation` VALUES(2, 3);
INSERT INTO `relation` VALUES(2, 4);

-- --------------------------------------------------------

--
-- Structure de la table `SOUS_COMPETENCE`
--

CREATE TABLE `SOUS_COMPETENCE` (
  `ID_SOUS_COM` int(10) NOT NULL,
  `ID_COMP` int(10) NOT NULL,
  `NOTE` int(10) NOT NULL,
  `NOM` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `SOUS_COMPETENCE`
--


-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `name` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL,
  `id_tuteur` int(11) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

INSERT INTO `user` VALUES(1, 'Benoit', '1', 'tuteur', 0);
INSERT INTO `user` VALUES(2, 'David', '2', 'tuteur', 0);
INSERT INTO `user` VALUES(3, 'Mercury', '3', 'eleve', 1);
INSERT INTO `user` VALUES(4, 'Venus', '4', 'eleve', 1);
