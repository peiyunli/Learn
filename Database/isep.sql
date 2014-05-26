-- phpMyAdmin SQL Dump
-- version 3.5.7
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Lun 26 Mai 2014 à 16:45
-- Version du serveur: 5.5.29
-- Version de PHP: 5.4.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données: `isep`
--

-- --------------------------------------------------------

--
-- Structure de la table `competences`
--

CREATE TABLE `competences` (
  `id_competence` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `id_evaluation` int(11) NOT NULL,
  PRIMARY KEY (`id_competence`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

CREATE TABLE `evaluation` (
  `id_evaluation` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `id_student` int(11) NOT NULL,
  `id_tutor` int(11) NOT NULL,
  PRIMARY KEY (`id_evaluation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `sous_competences`
--

CREATE TABLE `sous_competences` (
  `id_sous_competence` int(11) NOT NULL AUTO_INCREMENT,
  `id_competence` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `grade` varchar(10) NOT NULL,
  PRIMARY KEY (`id_sous_competence`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `forname` varchar(250) NOT NULL,
  `mail` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `user_type` int(11) NOT NULL,
  `id_sup` int(11) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id_user`, `name`, `forname`, `mail`, `password`, `user_type`, `id_sup`) VALUES
(1, 'admin', 'admin', 'admin@isep.fr', 'admin', 1, 0),
(2, 'eleve_prenom', 'eleve_nom', 'eleve@example.com', 'eleve', 2, 2),
(3, 'tuteur_nom', 'tuteur_prenom', 'tuteur@exemple.com', 'tuteur', 3, 3),
(4, 'responsable_nom', 'responsable_prenom', 'responsable@exemple.com', 'responsable', 4, 0);
