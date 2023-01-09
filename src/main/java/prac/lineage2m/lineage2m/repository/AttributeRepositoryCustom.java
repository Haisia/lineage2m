package prac.lineage2m.lineage2m.repository;

import prac.lineage2m.lineage2m.entity.Attribute;

import java.util.Optional;

public interface AttributeRepositoryCustom {
  Optional<Attribute> findByItemInfoPk(Long itemInfoPk);
}
