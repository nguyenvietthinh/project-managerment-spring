package vn.elca.training.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import vn.elca.training.ApplicationLauncher;
import vn.elca.training.dom.Group;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationLauncher.class)
@WebIntegrationTest
@TransactionConfiguration
public class IGroupRepositoryTest {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private IGroupRepository groupRepository;

    @Test
    public void testCountAllGroup() {
        groupRepository.save(new Group());
        Assert.assertEquals(1, groupRepository.count());
    }
}
